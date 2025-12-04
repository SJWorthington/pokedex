package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.anotherpokedex.ui.preview.previewLazyPagingItems
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonListViewModel.NavigationEvent.ToPokemonDetail
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import com.example.anotherpokedex.ui.screens.pokemonlist.models.SampleData
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonListRoute(
    modifier: Modifier,
    navigateToPokemonDetails: (String) -> Unit,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
    val interactions = viewModel.interactions
    val navigationEvents = viewModel.navigationEvents

    LaunchedEffect(Unit) {
        navigationEvents.collect { event ->
            when {
                event is ToPokemonDetail -> navigateToPokemonDetails(event.name)
            }
        }
    }

    PokemonListScreen(
        modifier = modifier,
        pokemonList = pokemonList,
        interactions = interactions,
    )
}

@Composable
fun PokemonListScreen(
    modifier: Modifier,
    interactions: PokemonListViewModel.Interactions,
    pokemonList: LazyPagingItems<PokemonUiModel>
) {
    //Todo - reinstate if we keep anything in state
    //val state = viewModel.state.collectAsState()
    val gridState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            state = gridState,
            columns = GridCells.Fixed(2)
        ) {
            items(
                count = pokemonList.itemCount,
                key = { index ->
                    // Think this actually uses index by default, but it fixed a bug I accidentally introduced
                    // by using the dexNumber as the index (Pokemon index from 1),
                    // so letting this live for future reference
                    index
                }
            ) { index ->
                pokemonList[index]?.let { pokemon ->
                    val backgroundBrush = remember(pokemon.types) {
                        val colors = listOf(
                            pokemon.types.first.getTypeColor(),
                            pokemon.types.second?.getTypeColor()
                                ?: pokemon.types.first.getTypeColor()
                        )
                        Brush.verticalGradient(colors)
                    }

                    PokemonGridCard(
                        pokemon = pokemon,
                        backgroundBrush = backgroundBrush,
                        onClick = { interactions.onClickPokemon(pokemon.name) },
                        onClickFavourite = { interactions.onClickFavourite(pokemon.id) },
                        onClickUnfavourite = { interactions.onClickUnfavourite(pokemon.id) }
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
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonListScreen() {
    val listItems = previewLazyPagingItems(
        listOf(
            SampleData.shinyBulbasaur,
            SampleData.pikachu,
            SampleData.shinyBulbasaur,
            SampleData.pikachu
        )
    )

    val interactions = PokemonListViewModel.Interactions(
        onClickPokemon = {}, onClickFavourite = {}, onClickUnfavourite = {}
    )

    PokemonListScreen(
        modifier = Modifier,
        interactions = interactions,
        pokemonList = listItems
    )
}

