package com.example.anotherpokedex.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val results: List<NamedApiResource>,
    val next: String?,
    val previous: String?
)
