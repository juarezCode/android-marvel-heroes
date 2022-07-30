package com.juarez.marvelheroes.characters.domain

data class CharactersResponse(
    val code: Int,
    val data: CharactersData
)

data class CharactersData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val path: String,
    val extension: String
)