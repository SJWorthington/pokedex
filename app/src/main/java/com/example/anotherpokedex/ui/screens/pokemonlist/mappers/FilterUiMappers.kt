package com.example.anotherpokedex.ui.screens.pokemonlist.mappers

import com.example.anotherpokedex.ui.screens.pokemonlist.models.FavouriteFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.Filter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FiltersUiModel
import com.example.anotherpokedex.ui.screens.pokemonlist.models.GenerationFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.TypeFilter

fun List<Filter>.toUiModels(): List<FiltersUiModel> {
    val filters = mutableListOf<FiltersUiModel>()
    for (filter in this) {
        filters.add(
            when (filter) {
                is FavouriteFilter -> {
                    FiltersUiModel(
                        filter,
                        displayText = "Favourites",
                        null
                    )
                }

                is TypeFilter -> {
                    FiltersUiModel(
                        filter,
                        displayText = "Get Display text nicely",
                        subFilterOptions = emptyList(), // TODO
                    )
                }

                is GenerationFilter -> {
                    FiltersUiModel(
                        filter,
                        displayText = "Get Display text nicely",
                        subFilterOptions = emptyList(), //TODO
                    )
                }
            }
        )
    }

    return filters
}