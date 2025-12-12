package com.example.anotherpokedex.ui.screens.pokemonlist.models

import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.Type

// TODO - this class has become a bit of a hot mess, got it all pertains to filters but at slightly different layers
//  very messy, tidy

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

data class ListFilterOptions(
    val showFavourites: Boolean,
    val filterTypes: List<Type>,
    val filterGenerations: List<Generation>
)

data class FiltersUiModel(
    val displayText: String,
    val isEnabled: Boolean,
    val subFilterOptions: List<String>?
    // todo - subfilteroptions being a String will be very unhelpful when it comes to
    //  the user selecting new filters to apply, need to think this through
)