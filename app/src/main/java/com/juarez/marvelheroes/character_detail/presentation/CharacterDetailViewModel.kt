package com.juarez.marvelheroes.character_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juarez.marvelheroes.character_detail.domain.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    val getCharacterDetailUseCase: GetCharacterDetailUseCase
) : ViewModel() {

    private var _characterState = MutableStateFlow<CharacterState>(CharacterState.Empty)
    val characterState = _characterState.asStateFlow()

    fun getCharacterDetail(characterId: Int) {
        _characterState.update { CharacterState.Loading }
        getCharacterDetailUseCase(characterId).onEach { character ->
            _characterState.update { CharacterState.Success(character) }
        }.catch { throwable: Throwable ->
            _characterState.update { CharacterState.Error(throwable) }
        }.launchIn(viewModelScope)
    }

}