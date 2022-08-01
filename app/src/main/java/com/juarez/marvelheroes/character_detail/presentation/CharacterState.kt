package com.juarez.marvelheroes.character_detail.presentation

import com.juarez.marvelheroes.characters.domain.Character

sealed class CharacterState {
    object Loading : CharacterState()
    object Empty : CharacterState()
    data class Error(val throwable: Throwable) : CharacterState()
    data class Success(val data: Character) : CharacterState()
}