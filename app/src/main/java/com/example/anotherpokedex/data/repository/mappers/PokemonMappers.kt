package com.example.anotherpokedex.data.repository.mappers

import com.example.anotherpokedex.data.local.entities.PokemonEntity
import com.example.anotherpokedex.data.remote.model.PokemonDto
import com.example.anotherpokedex.data.remote.model.PokemonSpeciesDto
import com.example.anotherpokedex.domain.model.Generation
import com.example.anotherpokedex.domain.model.Pokemon
import com.example.anotherpokedex.domain.model.PokemonTypePairing

fun PokemonEntity.toDomain(): Pokemon = Pokemon(
    dexNumber = nationalDexNumber,
    name = name,
    imageUrl = imageUrl,
    shinyImageUrl = shinyImageUrl,
    types = PokemonTypePairing.fromStrings(types),
    isFavourite = isFavourite,
    initialGen = Generation.fromGenCode(initialGen)
)

fun createPokemonEntityFromDtos(
    pokemonDto: PokemonDto,
    speciesDto: PokemonSpeciesDto
) = PokemonEntity(
    nationalDexNumber = pokemonDto.id,
    name = pokemonDto.name,
    imageUrl = pokemonDto.sprites.other?.officialArtwork?.frontDefault ?: "",
    shinyImageUrl = pokemonDto.sprites.other?.officialArtwork?.frontShiny ?: "",
    types = pokemonDto.types.map { it.type.name.lowercase() },
    initialGen = Generation.fromApiName(speciesDto.initialGen?.name ?: "").genCode
)