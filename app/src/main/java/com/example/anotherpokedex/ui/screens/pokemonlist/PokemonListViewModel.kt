package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.usecases.GetPokemonListUseCase
import com.example.anotherpokedex.domain.usecases.ToggleIsFavouriteUseCase
import com.example.anotherpokedex.ui.screens.pokemonlist.mappers.toUiModels
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FavouriteFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.Filter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FiltersUiModel
import com.example.anotherpokedex.ui.screens.pokemonlist.models.GenerationFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import com.example.anotherpokedex.ui.screens.pokemonlist.models.TypeFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.usecases.ListFilteringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    getPokemonListUseCase: GetPokemonListUseCase,
    val toggleIsFavouriteUseCase: ToggleIsFavouriteUseCase,
    listFilteringUseCase: ListFilteringUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state

    private val listFilters: Flow<List<Filter>> =
        listFilteringUseCase.getPokemonListFilters()

    val pokemonPagingFlow: Flow<PagingData<PokemonUiModel>> =
        combine(
            getPokemonListUseCase(),
            listFilters
        ) { pagingData, listFilters ->
            pagingData.filter { isPokemonFiltered(listFilters, it) }
                .map { it.toPokemonListUiModel() }
        }.cachedIn(viewModelScope)

    //TODO - move this. Also make it less awful
    private fun isPokemonFiltered(
        filters: List<Filter>,
        pokemon: Pokemon
    ): Boolean {
        for (filter in filters) {
            when (filter) {
                is FavouriteFilter -> {
                    if (filter.isActive && !pokemon.isFavourite) return true
                }

                is TypeFilter -> {
                    if (
                        filter.isActive &&
                        pokemon.types.first !in filter.selectedTypes
                        || pokemon.types.second !in filter.selectedTypes
                    ) return true
                }

                is GenerationFilter -> {
                    if (
                        filter.isActive &&
                        pokemon.initialGen !in filter.selectedGens
                    ) return true
                }
            }
        }

        return false
    }

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents

    val interactions = Interactions(
        onClickPokemon = { name -> onClickPokemon(name) },
        onClickFavourite = { dexNumber -> onClickFavourite(dexNumber) },
        onClickUnfavourite = { dexNumber -> onClickUnfavourite(dexNumber) },
        {} //TODO - update datastore
    )

    private val shinyStatusMap = mutableMapOf<Int, Boolean>()

    init {
        viewModelScope.launch {
            listFilters.collect { filters ->
                _state.update { currentState ->
                    currentState.copy(
                        filters = filters.toUiModels()
                    )
                }
            }
        }
    }

    private fun Pokemon.toPokemonListUiModel(): PokemonUiModel {
        val isShiny = shinyStatusMap.getOrPut(dexNumber) {
            Random.nextInt(10) == 0
        }
        return PokemonUiModel(
            this.dexNumber,
            if (isShiny) this.shinyImageUrl else this.imageUrl,
            this.dexNumber.toString(),
            this.name,
            this.types,
            isShiny,
            this.isFavourite
        )
    }

    fun onClickPokemon(name: String) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.ToPokemonDetail(name))
        }
    }

    fun onClickFavourite(dexNumber: Int) {
        viewModelScope.launch {
            toggleIsFavouriteUseCase.invoke(dexNumber, true)
        }
    }

    fun onClickUnfavourite(dexNumber: Int) {
        viewModelScope.launch {
            toggleIsFavouriteUseCase.invoke(dexNumber, false)
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val filters: List<FiltersUiModel> = emptyList()
    )

    data class Interactions(
        val onClickPokemon: (String) -> Unit,
        val onClickFavourite: (Int) -> Unit,
        val onClickUnfavourite: (Int) -> Unit,
        val onClickFilterForFavourites: () -> Unit
    ) {
        companion object {
            val dummy = Interactions({}, {}, {}, {})
        }
    }

    sealed class NavigationEvent {
        data class ToPokemonDetail(val name: String) : NavigationEvent()
    }
}
