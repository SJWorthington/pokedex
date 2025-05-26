package com.example.anotherpokedex.data.repository.mappers

import com.example.anotherpokedex.data.remote.model.PokemonDto
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.model.PokemonTypePairing
import com.example.anotherpokedex.domain.model.Type

fun PokemonDto.toDomain(): Pokemon {
    val sortedTypes = types.sortedBy { it.slot }.map { it.type.name }

    //Default to normal isn't ideal but hey ho
    val firstType = sortedTypes.getOrNull(0)?.toDomainType() ?: Type.Normal
    val secondType = sortedTypes.getOrNull(1)?.toDomainType()

    return Pokemon(
        dexNumber = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = sprites.other?.officialArtwork?.frontDefault ?: sprites.frontDefault ?: "",
        shinyImageUrl = sprites.other?.officialArtwork?.frontShiny ?: sprites.frontShiny ?: "",
        types = PokemonTypePairing(
            first = firstType,
            second = secondType
        )
    )
}

fun String.toDomainType(): Type? {
    return when (this.lowercase()) {
        "fire" -> Type.Fire
        "water" -> Type.Water
        "grass" -> Type.Grass
        "electric" -> Type.Electric
        "normal" -> Type.Normal
        "ice" -> Type.Ice
        "fighting" -> Type.Fighting
        "poison" -> Type.Poison
        "ground" -> Type.Ground
        "flying" -> Type.Flying
        "psychic" -> Type.Psychic
        "bug" -> Type.Bug
        "rock" -> Type.Rock
        "ghost" -> Type.Ghost
        "dragon" -> Type.Dragon
        "dark" -> Type.Dark
        "steel" -> Type.Steel
        "fairy" -> Type.Fairy
        else -> null
    }
}