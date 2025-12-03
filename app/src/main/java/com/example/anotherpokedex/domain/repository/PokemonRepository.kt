package com.example.anotherpokedex.domain.repository

import androidx.paging.PagingData
import com.example.anotherpokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPagedPokemonList(): Flow<PagingData<Pokemon>>
    suspend fun updateIsFavourite(dexNumber: Int, isFavourite: Boolean)
}