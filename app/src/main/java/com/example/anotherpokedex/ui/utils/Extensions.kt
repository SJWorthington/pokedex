package com.example.anotherpokedex.ui.utils

import androidx.compose.ui.graphics.Color
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.theme.TypeColor

fun Type.getTypeColor(): Color {
    return when (this) {
        Type.Fire -> TypeColor.Fire
        Type.Water -> TypeColor.Water
        Type.Grass -> TypeColor.Grass
        Type.Electric -> TypeColor.Electric
        Type.Normal -> TypeColor.Normal
        Type.Ice -> TypeColor.Ice
        Type.Fighting -> TypeColor.Fighting
        Type.Poison -> TypeColor.Poison
        Type.Ground -> TypeColor.Ground
        Type.Flying -> TypeColor.Flying
        Type.Psychic -> TypeColor.Psychic
        Type.Bug -> TypeColor.Bug
        Type.Rock -> TypeColor.Rock
        Type.Ghost -> TypeColor.Ghost
        Type.Dragon -> TypeColor.Dragon
        Type.Dark -> TypeColor.Dark
        Type.Steel -> TypeColor.Steel
        Type.Fairy -> TypeColor.Fairy
    }
}