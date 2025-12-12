package com.example.anotherpokedex.data.remote.model

import kotlinx.serialization.SerialName

data class PokemonSpeciesDto(
    @SerialName("flavor_text_entries")
    val dexEntries: List<DexEntries>,
    @SerialName("egg_groups")
    val eggGroups: List<NamedApiResource>,
    val color: NamedApiResource?,
    val habitat: NamedApiResource?,
    val initialGen: NamedApiResource?
)

data class DexEntries(
    @SerialName("flavor_text")
    val dexEntry: String,
    val language: NamedApiResource
)