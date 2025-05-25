package com.example.anotherpokedex.ui.screens.pokemondetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonDetail(
    modifier: Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    Box(
        modifier = modifier.then(Modifier.fillMaxSize()),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Pokemon Id is ${state.value.pokemonId}")
    }
}