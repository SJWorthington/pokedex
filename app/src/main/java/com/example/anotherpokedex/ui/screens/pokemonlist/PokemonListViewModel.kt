package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.lifecycle.ViewModel
import com.example.anotherpokedex.ui.screens.pokemonlist.composables.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(

) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<PokemonUiModel>>(emptyList())
    val pokemonList: StateFlow<List<PokemonUiModel>> = _pokemonList

    init {
        _pokemonList.value = listOf(
            PokemonUiModel(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0001",
                "Bulbasaur",
                PokemonTypePairing(Type.Grass, Type.Fairy),
            ),
            PokemonUiModel(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0004",
                "Squirtle",
                PokemonTypePairing(Type.Water, Type.Flying),
            ),
            PokemonUiModel(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0007",
                "Charmander",
                PokemonTypePairing(Type.Fire, Type.Dragon),
            ),
            PokemonUiModel(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0025",
                "Pikachu",
                PokemonTypePairing(Type.Electric, null),
            )
        )
    }
}

data class PokemonUiModel(
    val imageUri: String,
    val readableNumber: String,
    val name: String,
    val types: PokemonTypePairing
)

//TODO - move this to data package
data class PokemonTypePairing(
    val first: Type,
    val second: Type?
)