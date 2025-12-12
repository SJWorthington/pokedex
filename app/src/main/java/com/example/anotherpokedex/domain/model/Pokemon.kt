package com.example.anotherpokedex.domain.model

data class Pokemon(
    val dexNumber: Int,
    val name: String,
    val imageUrl: String,
    val shinyImageUrl: String,
    val types: PokemonTypePairing,
    val isFavourite: Boolean,
    val initialGen: Generation
)

data class PokemonTypePairing(
    val first: Type,
    val second: Type? = null
) {
    companion object {
        fun fromStrings(types: List<String>): PokemonTypePairing {
            val mapped = types.map { Type.fromApiName(it) }
            val first = mapped.getOrNull(0) ?: Type.UNKNOWN
            val second = mapped.getOrNull(1)
            return PokemonTypePairing(first, second)
        }
    }
}

enum class Type {
    Fire, Water, Grass, Electric, Normal, Ice,
    Fighting, Poison, Ground, Flying, Psychic, Bug,
    Rock, Ghost, Dragon, Dark, Steel, Fairy, UNKNOWN;

    companion object {
        fun fromApiName(name: String): Type =
            entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: UNKNOWN
    }
}

enum class Generation(val genCode: Int) {
    Gen1(1), Gen2(2), Gen3(3), Gen4(4),
    Gen5(5), Gen6(6), Gen7(7), Gen8(8),
    Gen9(9), UNKNOWN(0);

    companion object {
        fun fromApiName(apiName: String?): Generation =
            when (apiName) {
                "generation-i" -> Gen1
                "generation-ii" -> Gen2
                "generation-iii" -> Gen3
                "generation-iv" -> Gen4
                "generation-v" -> Gen5
                "generation-vi" -> Gen6
                "generation-vii" -> Gen7
                "generation-viii" -> Gen8
                "generation-ix" -> Gen9
                else -> UNKNOWN
            }

        fun fromGenCode(genCode: Int): Generation =
            when (genCode) {
                1 -> Gen1
                2 -> Gen2
                3 -> Gen3
                4 -> Gen4
                5 -> Gen5
                6 -> Gen6
                7 -> Gen7
                8 -> Gen8
                9 -> Gen9
                else -> UNKNOWN
            }
    }
}


