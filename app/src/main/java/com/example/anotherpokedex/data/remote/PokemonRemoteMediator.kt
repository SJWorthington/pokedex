package com.example.anotherpokedex.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.anotherpokedex.data.local.db.PokemonDatabase
import com.example.anotherpokedex.data.local.entities.PokemonEntity
import com.example.anotherpokedex.data.repository.mappers.createPokemonEntityFromDtos
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

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
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    val lastItemIndex = state.pages
                        .flatMap { it.data }
                        .indexOfLast { it == lastItem }

                    if (lastItemIndex == -1) 0 else lastItemIndex + 1
                }
            }

            val response = apiService.getPokemonList(
                limit = state.config.pageSize,
                offset = loadKey
            )

            val detailedPokemon = response.results.mapNotNull { resource ->
                try {
                    coroutineScope {
                        val pokemonDeferred = async {
                            apiService.getPokemonDetails(resource.name)
                        }
                        val speciesDeferred = async {
                            apiService.getPokemonSpeciesDetails(resource.name)
                        }

                        createPokemonEntityFromDtos(
                            pokemonDto = pokemonDeferred.await(),
                            speciesDto = speciesDeferred.await()
                        )
                    }
                } catch (e: CancellationException) {
                    throw e
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