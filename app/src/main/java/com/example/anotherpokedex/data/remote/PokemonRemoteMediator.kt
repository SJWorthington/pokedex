package com.example.anotherpokedex.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.anotherpokedex.data.local.db.PokemonDatabase
import com.example.anotherpokedex.data.local.entities.PokemonEntity
import com.example.anotherpokedex.data.repository.mappers.toEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
    private val apiService: PokeApiService,
    private val database: PokemonDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            Log.d("Plantain", "LoadType = ${loadType.name}")
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.nationalDexNumber ?: 0
                }
            }

            val response = apiService.getPokemonList(
                limit = state.config.pageSize,
                offset = loadKey
            )

            val detailedPokemon = response.results.mapNotNull { resource ->
                try {
                    apiService.getPokemonDetails(resource.name).toEntity()
                } catch (e: Exception) {
                    null
                }
            }

            database.withTransaction {
                database.pokemonDao().insertAll(detailedPokemon)
            }

            MediatorResult.Success(endOfPaginationReached = response.next == null)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}