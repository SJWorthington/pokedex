package com.example.anotherpokedex.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val id: Int,
    val name: String,
    val types: List<TypeSlot>,
    @SerialName("sprites") val sprites: Sprites
)

@Serializable
data class TypeSlot(
    val slot: Int,
    val type: NamedApiResource
)

@Serializable
data class Sprites(
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("back_default") val backDefault: String?
)