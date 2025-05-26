package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonListViewModel.NavigationEvent.ToPokemonDetail
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonList(
    modifier: Modifier,
    navigateToPokemonDetails: (Int) -> Unit,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val interactions = viewModel.interactions

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when {
                event is ToPokemonDetail -> navigateToPokemonDetails(event.id)
            }
        }
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(state.value.pokemon) { pokemon ->
            val backgroundBrush = remember(pokemon.types) {
                val colors = listOf(
                    pokemon.types.first.getTypeColor(),
                    pokemon.types.second?.getTypeColor() ?: pokemon.types.first.getTypeColor()
                )
                Brush.verticalGradient(colors)
            }

            PokemonGridItem(
                pokemon = pokemon,
                backgroundBrush = backgroundBrush,
                onClick = { interactions.onClickPokemon(pokemon.id) }
            )
        }
    }
}
