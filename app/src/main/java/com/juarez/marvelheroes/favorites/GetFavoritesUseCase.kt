package com.juarez.marvelheroes.favorites

import com.juarez.marvelheroes.characters.data.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavoritesUseCase @Inject constructor(private val repository: CharactersRepository) {
    operator fun invoke(): Flow<List<CharacterEntity>> = repository.favoriteCharacters
}