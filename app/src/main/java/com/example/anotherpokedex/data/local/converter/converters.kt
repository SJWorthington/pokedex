package com.example.anotherpokedex.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class PokemonTypeConverter {
    @TypeConverter
    fun fromList(types: List<String>): String = Json.encodeToString(types)

    @TypeConverter
    fun toList(types: String): List<String> = Json.decodeFromString(types)
}