package com.example.anotherpokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.anotherpokedex.data.remote.PokeApiService
import com.example.anotherpokedex.data.remote.PokemonListPagingSource
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonListPagingSource(apiService) }
        ).flow
    }
}