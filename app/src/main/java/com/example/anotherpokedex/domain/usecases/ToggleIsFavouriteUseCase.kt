package com.example.anotherpokedex.domain.usecases

import com.example.anotherpokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class ToggleIsFavouriteUseCase @Inject constructor(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(dexNumber: Int, isFavourite: Boolean) {
        repository.updateIsFavourite(dexNumber, isFavourite)
    }
}