package com.juarez.marvelheroes.character_detail.domain

import com.juarez.marvelheroes.character_detail.data.CharacterDetailRepository
import com.juarez.marvelheroes.characters.domain.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterDetailUseCase @Inject constructor(private val repository: CharacterDetailRepository) {
    operator fun invoke(characterId: Int): Flow<Character> {
        return repository.getCharacterDetail(characterId)
    }
}