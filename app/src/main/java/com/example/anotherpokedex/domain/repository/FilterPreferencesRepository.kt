package com.example.anotherpokedex.domain.repository

import kotlinx.coroutines.flow.Flow

interface FilterPreferencesRepository {
    fun getIsFilteredForFavourites(): Flow<Boolean>
    suspend fun setIsFilteredForFavourites(enabled: Boolean)
}