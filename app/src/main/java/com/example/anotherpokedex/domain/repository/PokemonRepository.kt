package com.example.anotherpokedex.domain.repository

import com.example.anotherpokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon>
}