package com.example.anotherpokedex.data.repository

import androidx.datastore.core.DataStore
import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.domain.repository.FilterPreferencesRepository
import com.example.datastore.snippets.proto.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Settings>
): FilterPreferencesRepository {

    override fun getIsFilteredForFavourites(): Flow<Boolean> {
        return dataStore.data.map {
            it.filterFavourites
        }
    }

    override fun getFilteredTypes(): Flow<List<Type>> {
        //TODO - get data
        return flow { emptyList<Type>() }
    }

    override fun getFilteredGenerations(): Flow<List<Generation>> {
        //TODO - data
        return flow {emptyList<Generation>()}
    }

    override suspend fun setIsFilteredForFavourites(enabled: Boolean) {
        dataStore.updateData { current ->
            current.toBuilder()
                .setFilterFavourites(enabled)
                .build()
        }
    }
}