package com.juarez.marvelheroes.character_detail.data

import android.util.Log
import com.juarez.marvelheroes.api.MarvelApi
import com.juarez.marvelheroes.characters.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
) : CharacterDetailRepository {

    override fun getCharacterDetail(characterId: Int): Flow<Character> = flow {
        val response = marvelApi.getCharacterDetail(characterId)

        if (response.isSuccessful && response.body() != null) {
            Log.d("MARVEL", response.body()!!.data.results.toString())
            emit(response.body()!!.data.results.get(0))
        } else {
            throw Exception("${response.code()}")
        }
    }
}