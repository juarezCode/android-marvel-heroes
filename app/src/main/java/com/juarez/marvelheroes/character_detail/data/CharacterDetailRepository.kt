package com.juarez.marvelheroes.character_detail.data

import com.juarez.marvelheroes.characters.domain.Character
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {
    fun getCharacterDetail(characterId: Int): Flow<Character>
}