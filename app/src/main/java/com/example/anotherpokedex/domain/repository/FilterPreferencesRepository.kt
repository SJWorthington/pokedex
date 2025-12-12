package com.example.anotherpokedex.domain.repository

import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.Type
import kotlinx.coroutines.flow.Flow

interface FilterPreferencesRepository {
    fun getIsFilteredForFavourites(): Flow<Boolean>
    fun getFilteredTypes(): Flow<List<Type>>
    fun getFilteredGenerations(): Flow<List<Generation>>
    suspend fun setIsFilteredForFavourites(enabled: Boolean)
}