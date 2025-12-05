package com.example.anotherpokedex.domain.model

data class Pokemon(
    val dexNumber: Int,
    val name: String,
    val imageUrl: String,
    val shinyImageUrl: String,
    val types: PokemonTypePairing,
    val isFavourite: Boolean
)

data class PokemonTypePairing(
    val first: Type,
    val second: Type? = null
) {
    companion object {
        fun fromStrings(types: List<String>): PokemonTypePairing {
            val mapped = types.map { Type.fromName(it) }
            val first = mapped.getOrNull(0) ?: Type.Normal // TODO: handle gracefully (not Normal)
            val second = mapped.getOrNull(1)
            return PokemonTypePairing(first, second)
        }
    }
}

enum class Type {
    Fire, Water, Grass, Electric, Normal, Ice,
    Fighting, Poison, Ground, Flying, Psychic, Bug,
    Rock, Ghost, Dragon, Dark, Steel, Fairy;

    companion object {
        //TODO - stop defaulting to normal
        fun fromName(name: String): Type =
            entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: Normal
    }
}

enum class Generation {
    RedBlue, GoldSilver, RubySapphire, DiamondPearl, BlackWhite, XY, SunMoon, SwordShield, ScarletViolet
}


