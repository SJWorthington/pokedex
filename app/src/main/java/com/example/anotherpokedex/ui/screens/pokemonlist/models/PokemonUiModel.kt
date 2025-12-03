package com.example.anotherpokedex.ui.screens.pokemonlist.models

import com.example.anotherpokedex.domain.model.PokemonTypePairing

data class PokemonUiModel(
    val id: Int,
    val imageUri: String,
    val readableNumber: String,
    val name: String,
    val types: PokemonTypePairing,
    val displayShiny: Boolean,
    val isFavourite: Boolean
)