package com.juarez.marvelheroes.characters.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.juarez.marvelheroes.api.MarvelApi
import com.juarez.marvelheroes.characters.domain.Character
import com.juarez.marvelheroes.favorites.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            CharactersPagingSource(marvelApi)
        }).flow
    }

    override val favoriteCharacters: Flow<List<CharacterEntity>> = flow {
        emit(
            listOf(
                CharacterEntity(1, "some", "asd"),
                CharacterEntity(2, "some", "asd"),
                CharacterEntity(3, "some", "asd"),
                CharacterEntity(4, "some", "asd"),
            )
        )
    }
}