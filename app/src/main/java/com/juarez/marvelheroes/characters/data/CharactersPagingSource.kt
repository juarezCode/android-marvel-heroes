package com.juarez.marvelheroes.characters.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.juarez.marvelheroes.api.MarvelApi
import com.juarez.marvelheroes.characters.domain.Character
import com.juarez.marvelheroes.common.Constants
import retrofit2.HttpException
import java.io.IOException

class CharactersPagingSource(private val marvelApi: MarvelApi) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        try {
            val nextPage = params.key ?: 1
            val offset = (nextPage - 1) * 20
            val response = marvelApi.getCharacters(offset = offset)
            if (response.isSuccessful && response.body() != null) {
                val characters = response.body()!!.data.results
                val total = response.body()!!.data.total

                return LoadResult.Page(
                    data = characters,
                    prevKey = null,
                    nextKey = if (offset >= total) null else nextPage + 1
                )
            }

            return LoadResult.Error(Exception("Error in service"))

        } catch (exception: Exception) {
            if (exception is HttpException) return LoadResult.Error(Throwable("un Http Exception"))
            if (exception is IOException) return LoadResult.Error(Throwable(Constants.CONNECTION_ERROR))

            return LoadResult.Error(exception)
        }
    }
}