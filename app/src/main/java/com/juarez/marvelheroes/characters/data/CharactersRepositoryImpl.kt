package com.juarez.marvelheroes.characters.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.juarez.marvelheroes.api.MarvelApi
import com.juarez.marvelheroes.characters.domain.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            CharactersPagingSource(marvelApi)
        }).flow
    }
}