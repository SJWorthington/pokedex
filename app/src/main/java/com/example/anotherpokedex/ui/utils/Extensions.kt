package com.example.anotherpokedex.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.theme.typeColors

@Composable
fun Type.getTypeColor(): Color {
    val colours = MaterialTheme.typeColors
    return when (this) {
        Type.Fire -> colours.fire
        Type.Water -> colours.water
        Type.Grass -> colours.grass
        Type.Electric -> colours.electric
        Type.Normal -> colours.normal
        Type.Ice -> colours.ice
        Type.Fighting -> colours.fighting
        Type.Poison -> colours.poison
        Type.Ground -> colours.ground
        Type.Flying -> colours.flying
        Type.Psychic -> colours.psychic
        Type.Bug -> colours.bug
        Type.Rock -> colours.rock
        Type.Ghost -> colours.ghost
        Type.Dragon -> colours.dragon
        Type.Dark -> colours.dark
        Type.Steel -> colours.steel
        Type.Fairy -> colours.fairy
    }
}