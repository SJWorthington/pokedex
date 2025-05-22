package com.example.anotherpokedex.data.remote

import com.example.anotherpokedex.data.remote.model.PokemonDto
import com.example.anotherpokedex.data.remote.model.PokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PokeApiService(
    private val client: HttpClient
) {
    private val baseUrl = "https://pokeapi.co/api/v2/"

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): PokemonListResponse {
        return client.get("${baseUrl}pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

    //TODO - probably a better way to construct this URL
    suspend fun getPokemonDetails(name: String) : PokemonDto {
        return client.get("${baseUrl}pokemon/$name").body()
    }
}