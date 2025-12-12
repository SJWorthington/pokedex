package com.example.anotherpokedex.ui.screens.pokemonlist.mappers

import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FiltersUiModel
import com.example.anotherpokedex.ui.screens.pokemonlist.models.ListFilterOptions

//TODO - make this less awful
fun ListFilterOptions.toUiModels() : List<FiltersUiModel> {
    val favouriteFilter = FiltersUiModel(
        displayText = "Favourites",
        isEnabled = this.showFavourites,
        null
    )
    val selectedTypeString = this.filterTypes
        .joinToString(", ") { it.toString() }
        .let { full ->
            if (full.length > 20) full.take(20) + "…" else full
        }
    val typeFilter = FiltersUiModel(
        selectedTypeString.ifEmpty { "Types" },
        isEnabled = this.filterTypes.isNotEmpty(),
        subFilterOptions = Type.entries.map { it.toString() }
    )
    val selectedGensString = this.filterGenerations
        .joinToString(", ") { it.toString() }
        .let { full ->
            if (full.length > 20) full.take(20) + "…" else full
        }
    val generationFilter = FiltersUiModel(
        displayText = selectedGensString.ifEmpty { "Generation" },
        isEnabled = this.filterGenerations.isNotEmpty(),
        subFilterOptions = Generation.entries.map { it.toString() }
    )
    return listOf(
        favouriteFilter, typeFilter, generationFilter
    )
}