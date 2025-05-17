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

    private val _pokemonList = MutableStateFlow<List<PokeyMan>>(emptyList())
    val pokemonList: StateFlow<List<PokeyMan>> = _pokemonList

    init {
        _pokemonList.value = listOf(
            PokeyMan(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0001",
                "Bulbasaur",
                PokeyManTypePairing(Type.Grass, Type.Fairy),
            ),
            PokeyMan(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0004",
                "Squirtle",
                PokeyManTypePairing(Type.Water, Type.Flying),
            ),
            PokeyMan(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0007",
                "Charmander",
                PokeyManTypePairing(Type.Fire, Type.Dragon),
            ),
            PokeyMan(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/94.png",
                "#0025",
                "Pikachu",
                PokeyManTypePairing(Type.Electric, null),
            )
        )
    }
}

data class PokeyMan(
    val imageUri: String,
    val readableNumber: String,
    val name: String,
    val types: PokeyManTypePairing
)

data class PokeyManTypePairing(
    val first: Type,
    val second: Type?
)