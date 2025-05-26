package com.example.anotherpokedex.ui.screens.pokemonlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.usecases.GetPokemonListUseCase
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    val pokemonPagingFlow: Flow<PagingData<PokemonUiModel>> =
        getPokemonListUseCase()
            .map { pagingData: PagingData<Pokemon> ->
                pagingData.map { it.toPokemonListUiModel() }
            }
            .cachedIn(viewModelScope)

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents

    val interactions = Interactions(
        onClickPokemon = { name -> onClickPokemon(name) }
    )

    private val shinyStatusMap = mutableMapOf<Int, Boolean>()

    private fun Pokemon.toPokemonListUiModel(): PokemonUiModel {
        val isShiny = shinyStatusMap.getOrPut(dexNumber) {
            Random.nextInt(10) == 0
        }
        Log.d("Plantain", "DexNumber = ${this.dexNumber}")
        return PokemonUiModel(
            this.dexNumber,
            if (isShiny) this.shinyImageUrl else this.imageUrl,
            this.dexNumber.toString(),
            this.name,
            this.types,
            isShiny
        )
    }

    fun onClickPokemon(name: String) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.ToPokemonDetail(name))
        }
    }

    //TODO - retaining this since there will (probably?) be other uses for it, delete if not
    data class State(
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    data class Interactions(
        val onClickPokemon: (String) -> Unit
    )

    sealed class NavigationEvent {
        data class ToPokemonDetail(val name: String) : NavigationEvent()
    }
}
