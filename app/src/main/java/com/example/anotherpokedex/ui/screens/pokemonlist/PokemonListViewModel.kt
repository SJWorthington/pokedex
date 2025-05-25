package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anotherpokedex.domain.usecases.GetPokemonListUseCase
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents

    val interactions = Interactions (
        onClickPokemon = { id -> onClickPokemon(id) }
    )

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val pokemon = getPokemonListUseCase.invoke().map {
                    PokemonUiModel(
                        it.dexNumber,
                        it.imageUrl,
                        it.dexNumber.toString(),
                        it.name,
                        it.types,
                    )
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        pokemon = pokemon,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Something went wrong: ${e.localizedMessage}"
                    )
                }
            }
        }
    }

    fun onClickPokemon(id: Int) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.ToPokemonDetail(id))
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val pokemon: List<PokemonUiModel> = emptyList(),
        val errorMessage: String? = null
    )

    data class Interactions (
        val onClickPokemon: (Int) -> Unit
    )

    sealed class NavigationEvent {
        data class ToPokemonDetail(val id: Int) : NavigationEvent()
    }
}
