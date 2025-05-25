package com.example.anotherpokedex.domain.model

data class Pokemon(
    val dexNumber: Int,
    val name: String,
    val imageUrl: String,
    val types: PokemonTypePairing
)

data class PokemonTypePairing(
    val first: Type,
    val second: Type?
)

enum class Type {
    Fire,
    Water,
    Grass,
    Electric,
    Normal,
    Ice,
    Fighting,
    Poison,
    Ground,
    Flying,
    Psychic,
    Bug,
    Rock,
    Ghost,
    Dragon,
    Dark,
    Steel,
    Fairy,
}
