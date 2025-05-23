package com.example.anotherpokedex.ui.screens.pokemonlist.models

import com.example.anotherpokedex.domain.model.PokemonTypePairing

data class PokemonUiModel(
    val imageUri: String,
    val readableNumber: String,
    val name: String,
    val types: PokemonTypePairing
)