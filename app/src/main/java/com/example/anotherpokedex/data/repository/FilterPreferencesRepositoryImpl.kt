package com.example.anotherpokedex.data.repository

import androidx.datastore.core.DataStore
import com.example.anotherpokedex.domain.repository.FilterPreferencesRepository
import com.example.datastore.snippets.proto.Settings
import kotlinx.coroutines.flow.Flow
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

    override suspend fun setIsFilteredForFavourites(enabled: Boolean) {
        dataStore.updateData { current ->
            current.toBuilder()
                .setFilterFavourites(enabled)
                .build()
        }
    }
}