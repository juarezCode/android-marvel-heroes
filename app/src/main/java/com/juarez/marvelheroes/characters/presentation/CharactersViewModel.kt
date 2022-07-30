package com.juarez.marvelheroes.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juarez.marvelheroes.characters.domain.Character
import com.juarez.marvelheroes.characters.domain.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    val characters: Flow<PagingData<Character>> = getCharactersUseCase()
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
}