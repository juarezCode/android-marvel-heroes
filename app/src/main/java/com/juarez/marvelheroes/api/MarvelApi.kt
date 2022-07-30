package com.juarez.marvelheroes.api

import com.juarez.marvelheroes.characters.domain.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 20
    ): Response<CharactersResponse>

}