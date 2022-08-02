package com.juarez.marvelheroes.characters.domain

data class CharactersPaginationResponse(
    val characters: List<Character>,
    val total: Int
)
