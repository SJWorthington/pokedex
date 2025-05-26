package com.example.anotherpokedex.data.remote.model

data class PokemonSpeciesDto(
    val flavor_text_entries: List<FlavorTextEntryDto>,
    val egg_groups: List<NamedApiResource>,
    val color: NamedApiResource?,
    val habitat: NamedApiResource?
)

data class FlavorTextEntryDto(
    val flavor_text: String,
    val language: NamedApiResource
)