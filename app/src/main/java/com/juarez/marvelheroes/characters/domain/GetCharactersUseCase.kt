package com.juarez.marvelheroes.characters.domain

import androidx.paging.PagingData
import com.juarez.marvelheroes.characters.data.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    operator fun invoke(): Flow<PagingData<Character>> = repository.getCharacters()
}