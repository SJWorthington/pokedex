package com.example.anotherpokedex.ui.screens.pokemonlist.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anotherpokedex.ui.theme.TypeColor

//TODO - likely a generic composable, shift into a more reusable space, maybe give it an easy argument to style in 2 ways?
// Like enum class Style {BIG, SMALL}
@Composable
fun PokemonListTypePill(modifier: Modifier, type: Type) {
    val backgroundColor = getTypeColor(type)

    Box(
        modifier = modifier.then(
            Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    ) {
        Text(
            text = type.name,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

//TODO - somewhere more sensible
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

//TODO - Also somewhere more sensible
fun getTypeColor(type: Type): Color {
    return when (type) {
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