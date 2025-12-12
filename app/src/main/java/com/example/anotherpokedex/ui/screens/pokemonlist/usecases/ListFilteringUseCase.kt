package com.example.anotherpokedex.ui.screens.pokemonlist.usecases

import com.example.anotherpokedex.domain.repository.FilterPreferencesRepository
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FavouriteFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.Filter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.GenerationFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.TypeFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class ListFilteringUseCase @Inject constructor(
    val filterPreferencesRepo: FilterPreferencesRepository
) {

    fun getPokemonListFilters(): Flow<List<Filter>> {
        val hello = combine(
            filterPreferencesRepo.getIsFilteredForFavourites(),
            filterPreferencesRepo.getFilteredTypes(),
            filterPreferencesRepo.getFilteredGenerations()
        ) { favouriteFilter, typeFilters, genFilters ->
            listOf(
                FavouriteFilter(
                    isActive = favouriteFilter
                ),
                TypeFilter(
                    isActive = typeFilters.isNotEmpty(),
                    typeFilters.toSet()
                ),
                GenerationFilter(
                    isActive = genFilters.isNotEmpty(),
                    genFilters.toSet()
                )
            )
        }

        return hello
    }
}
