package com.example.anotherpokedex.data.repository.mappers

import com.example.anotherpokedex.data.local.entities.PokemonEntity
import com.example.anotherpokedex.data.remote.model.PokemonDto
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.model.PokemonTypePairing
import com.example.anotherpokedex.domain.model.Type

fun PokemonDto.toDomain(): Pokemon {
    val sortedTypes = types.sortedBy { it.slot }.map { it.type.name }

    //Default to normal isn't ideal but hey ho
    val firstType = sortedTypes.getOrNull(0)?.let { Type.fromName(it) } ?: Type.Normal
    val secondType = sortedTypes.getOrNull(1)?.let { Type.fromName(it) }

    return Pokemon(
        dexNumber = id,
        name = name.replaceFirstChar { it.uppercase() },
        imageUrl = sprites.other?.officialArtwork?.frontDefault ?: sprites.frontDefault ?: "",
        shinyImageUrl = sprites.other?.officialArtwork?.frontShiny ?: sprites.frontShiny ?: "",
        types = PokemonTypePairing(
            first = firstType,
            second = secondType
        ),
        isFavourite = false
    )
}

fun PokemonEntity.toDomain(): Pokemon = Pokemon(
    dexNumber = nationalDexNumber,
    name = name,
    imageUrl = imageUrl,
    shinyImageUrl = shinyImageUrl,
    types = PokemonTypePairing.fromStrings(types),
    isFavourite = isFavourite
)

fun Pokemon.toEntity(): PokemonEntity = PokemonEntity(
    nationalDexNumber = dexNumber,
    name = name,
    imageUrl = imageUrl,
    shinyImageUrl = shinyImageUrl,
    types = listOfNotNull(types.first.name, types.second?.name)
)

fun PokemonDto.toEntity(): PokemonEntity {
    return PokemonEntity(
        nationalDexNumber = id,
        name = name,
        imageUrl = sprites.other?.officialArtwork?.frontDefault ?: "",
        shinyImageUrl = sprites.other?.officialArtwork?.frontShiny ?: "",
        types = types.map { it.type.name.lowercase() }
    )
}