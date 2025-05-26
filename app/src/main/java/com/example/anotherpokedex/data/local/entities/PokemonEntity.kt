package com.example.anotherpokedex.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val nationalDexNumber: Int,
    val name: String,
    val imageUrl: String,
    val shinyImageUrl: String,
    val types: List<String>
)