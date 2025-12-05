package com.example.anotherpokedex.ui.screens.pokemonlist.models

import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.PokemonTypePairing
import com.example.anotherpokedex.domain.model.Type

object SampleData {

    val pikachu = PokemonUiModel(
        id = 1,
        imageUri = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        readableNumber = "#025",
        name = "Pikachu",
        types = PokemonTypePairing(
            first = Type.Electric,
            second = null
        ),
        displayShiny = false,
        isFavourite = true
    )

    val shinyBulbasaur = PokemonUiModel(
        id = 1,
        imageUri = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png",
        readableNumber = "#001",
        name = "Bulbasaur",
        types = PokemonTypePairing(
            first = Type.Grass,
            second = Type.Poison
        ),
        displayShiny = true,
        isFavourite = false
    )

    val sampleFilters = listOf(
        FiltersUiModel (
            "Favourites",
            false,
            null
        ),
        FiltersUiModel (
            "Fire, Electric",
            true,
            listOf(Type.entries.toTypedArray().toString())
        ),
        FiltersUiModel (
            "First Appearance",
            false,
            listOf(Generation.entries.toTypedArray().toString())
        )
    )
}