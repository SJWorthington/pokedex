package com.example.anotherpokedex.data.repository

import android.util.Log
import com.example.anotherpokedex.data.remote.PokeApiService
import com.example.anotherpokedex.data.repository.mappers.toDomain
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val apiService: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        val listResponse = apiService.getPokemonList()

        return listResponse.results.mapNotNull { resource ->
            try {
                val detail = apiService.getPokemonDetails(resource.name)
                detail.toDomain()
            } catch (e: Exception) {
                Log.e("PokemonRepositoryImpl", "Failed to fetch details for ${resource.name}", e)
                null
            }
        }
    }
}