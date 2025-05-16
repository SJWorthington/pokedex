package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    // Dependencies as needed
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<String>>(emptyList())
    val pokemonList: StateFlow<List<String>> = _pokemonList

    init {
        _pokemonList.value = listOf("Bulbasaur", "Charmander", "Squirtle", "Pikachu")
    }
}