package com.example.anotherpokedex.ui.screens.pokemonlist.usecases

import com.example.anotherpokedex.domain.repository.FilterPreferencesRepository
import com.example.anotherpokedex.ui.screens.pokemonlist.models.ListFilterOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ListFilteringUseCase @Inject constructor(
    val filterPreferencesRepo: FilterPreferencesRepository
) {

    //TODO - a lot to do here once the other filters are in protostore
    fun getPokemonListFilters(): Flow<ListFilterOptions> {
        return filterPreferencesRepo.getIsFilteredForFavourites().map {
            ListFilterOptions(
                it,
                emptyList(),
                emptyList()
            )
        }
    }
}
