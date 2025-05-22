package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anotherpokedex.domain.usecases.GetPokemonListUseCase
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    //TODO - convert this to hold a screen state model
    private val _pokemonList = MutableStateFlow<List<PokemonUiModel>>(emptyList())
    val pokemonList: StateFlow<List<PokemonUiModel>> = _pokemonList

    init {
        viewModelScope.launch {
            _pokemonList.value = getPokemonListUseCase.invoke().map {
                PokemonUiModel(
                    it.imageUrl,
                    it.number.toString(),
                    it.name,
                    it.types,
                )
            }
        }
    }
}
