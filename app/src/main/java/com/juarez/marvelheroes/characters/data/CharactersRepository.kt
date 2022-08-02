package com.juarez.marvelheroes.characters.data

import androidx.paging.PagingData
import com.juarez.marvelheroes.characters.domain.Character
import com.juarez.marvelheroes.favorites.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    val favoriteCharacters: Flow<List<CharacterEntity>>
}