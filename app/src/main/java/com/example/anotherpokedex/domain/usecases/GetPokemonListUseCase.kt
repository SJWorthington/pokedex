package com.example.anotherpokedex.domain.usecases

import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.repository.PokemonRepository
import javax.inject.Inject


class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): List<Pokemon> {
        return repository.getPokemonList()
    }
}