package com.example.anotherpokedex.ui.screens.pokemonlist.models

import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.Type

sealed interface Filter {
    val isActive: Boolean
}

data class FavouriteFilter(
    override val isActive: Boolean,
) : Filter

data class TypeFilter(
    override val isActive: Boolean,
    val selectedTypes: Set<Type>
) : Filter

data class GenerationFilter(
    override val isActive: Boolean,
    val selectedGens: Set<Generation>
) : Filter