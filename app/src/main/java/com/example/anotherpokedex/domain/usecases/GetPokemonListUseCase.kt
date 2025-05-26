package com.example.anotherpokedex.domain.usecases

import androidx.paging.PagingData
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPokemonListUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return repository.getPagedPokemonList()
    }
}