package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonListViewModel.NavigationEvent.ToPokemonDetail
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonList(
    modifier: Modifier,
    navigateToPokemonDetails: (String) -> Unit,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    //Todo - reinstate if we keep anything in state
    //val state = viewModel.state.collectAsState()
    val pokemonList = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
    val interactions = viewModel.interactions

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when {
                event is ToPokemonDetail -> navigateToPokemonDetails(event.name)
            }
        }
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(pokemonList.itemCount) { index ->
            pokemonList.get(index)?.let { pokemon ->
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
                    onClick = { interactions.onClickPokemon(pokemon.name) }
                )
            }
        }

        item(span = { GridItemSpan(2) }) {
            PokemonListLoadStateItem(loadState = pokemonList.loadState.refresh)
        }

        item(span = { GridItemSpan(2) }) {
            PokemonListLoadStateItem(
                loadState = pokemonList.loadState.append,
                errorPrefix = "Couldn't load more Pokémon"
            )
        }
    }
}
