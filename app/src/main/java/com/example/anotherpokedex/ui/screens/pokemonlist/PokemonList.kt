package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.anotherpokedex.ui.screens.pokemonlist.composables.PokemonListTypePill
import com.example.anotherpokedex.ui.screens.pokemonlist.composables.getTypeColor

@Composable
fun PokemonList(
    modifier: Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList = viewModel.pokemonList.collectAsState()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList.value) {
            PokemonGridItem(it)
        }
    }
}

@Composable
fun PokemonGridItem(
    pokemon: PokemonUiModel,
    modifier: Modifier = Modifier
) {
    val borderBrush = remember(pokemon.types) {
        val colors = when (val second = pokemon.types.second) {
            null -> List(2) { getTypeColor(pokemon.types.first) }
            else -> listOf(getTypeColor(pokemon.types.first), getTypeColor(second))
        }
        Brush.verticalGradient(colors)
    }

    Box(
        modifier = modifier
            .padding(8.dp)
            .background(brush = borderBrush, shape = RoundedCornerShape(16.dp))
            .padding(2.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
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
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = pokemon.readableNumber, style = MaterialTheme.typography.bodySmall)
                Text(text = pokemon.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
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
        }
    }
}