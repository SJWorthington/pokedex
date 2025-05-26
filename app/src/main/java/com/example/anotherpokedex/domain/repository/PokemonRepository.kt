package com.example.anotherpokedex.domain.repository

import androidx.paging.PagingData
import com.example.anotherpokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
}