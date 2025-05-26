package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonListViewModel.NavigationEvent.ToPokemonDetail

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
           // PokemonGridItem(
            PokemonGridItem(
                pokemon = pokemon,
                onClick = { interactions.onClickPokemon(pokemon.id) }
            )
        }
    }
}
