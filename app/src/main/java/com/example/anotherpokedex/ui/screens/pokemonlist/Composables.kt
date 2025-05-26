package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonGridItem(
    modifier: Modifier = Modifier,
    pokemon: PokemonUiModel,
    backgroundBrush: Brush,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(brush = backgroundBrush, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp)) // ensures overlay & content match shape
            .clickable { onClick(pokemon.id) }
    ) {
        if (pokemon.displayShiny) {
            PokemonGlowOverlay(modifier = Modifier.matchParentSize())
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.imageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp)) // slightly smaller for inner image
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pokemon.readableNumber, style = MaterialTheme.typography.bodySmall)
            Text(text = pokemon.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            PokemonListTypeRow(pokemon)
        }
    }
}

@Composable
fun PokemonListTypePill(modifier: Modifier, type: Type) {
    val backgroundColor = type.getTypeColor()

    Box(
        modifier = modifier
            .shadow(2.dp, shape = RoundedCornerShape(50))
            .background(color = backgroundColor, shape = RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.6f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = type.name,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun PokemonListTypeRow(pokemon: PokemonUiModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.wrapContentWidth()
    ) {
        PokemonListTypePill(
            modifier = Modifier.padding(end = 4.dp),
            type = pokemon.types.first
        )
        pokemon.types.second?.let {
            PokemonListTypePill(
                modifier = Modifier.padding(end = 4.dp),
                type = it
            )
        }
    }
}

@Composable
fun PokemonGlowOverlay(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "shiny-glow")
    val glowAlpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Box(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Blue.copy(alpha = glowAlpha * 0.8f),
                    Color.Blue.copy(alpha = glowAlpha),
                    Color.Blue.copy(alpha = glowAlpha * 0.8f),
                    Color.Transparent
                )
            ),
            shape = RoundedCornerShape(16.dp)
        )
    )
}