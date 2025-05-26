package com.example.anotherpokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.anotherpokedex.data.local.db.PokemonDatabase
import com.example.anotherpokedex.data.remote.PokemonRemoteMediator
import com.example.anotherpokedex.data.repository.mappers.toDomain
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val remoteMediator: PokemonRemoteMediator,
    private val db: PokemonDatabase,
) : PokemonRepository {

    // TODO - delete if remoteMediator works
//    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 20,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { PokemonListPagingSource(apiService) }
//        ).flow
//    }

    override fun getPagedPokemonList(): Flow<PagingData<Pokemon>> {
        val pagingSourceFactory = { db.pokemonDao().getPagedPokemon() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }
}