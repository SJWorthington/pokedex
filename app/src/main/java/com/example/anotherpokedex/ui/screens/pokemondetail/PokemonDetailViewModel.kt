package com.example.anotherpokedex.ui.screens.pokemondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val pokemonName = savedStateHandle.get<String>("pokemonName")

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    pokemonName = pokemonName ?: "Keith"
                )
            }
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val pokemonName: String = "",
        val errorMessage: String? = null
    )
}