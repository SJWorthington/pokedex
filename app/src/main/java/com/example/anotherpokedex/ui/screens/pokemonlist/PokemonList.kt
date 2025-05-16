package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun PokemonList(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val thePokeymen = viewModel.pokemonList.collectAsState()

    Column {
        thePokeymen.value.forEach {
            PokemonListItem(it)
        }
    }
}

@Composable
fun PokemonListItem(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}