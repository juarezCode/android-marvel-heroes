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
    val thumbnail: Thumbnail,
    val comics: Comic,
    val series: Serie
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Comic(
    val items: List<ComicItem>
)

data class ComicItem(
    val name: String
)

data class Serie(
    val items: List<SerieItem>
)

data class SerieItem(
    val name: String
)