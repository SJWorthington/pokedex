package com.example.anotherpokedex.ui.screens.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonDetail(
    modifier: Modifier,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    val dummyPokemonDetail = PokemonDetailUiModel(
        id = 25,
        name = "Pikachu",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png",
        types = listOf(Type.Electric),
        height = 0.4f,
        weight = 6.0f,
        baseStats = listOf(
            Stat(name = "HP", value = 35),
            Stat(name = "Attack", value = 55),
            Stat(name = "Defense", value = 40),
            Stat(name = "Sp. Atk", value = 50),
            Stat(name = "Sp. Def", value = 50),
            Stat(name = "Speed", value = 90)
        ),
        flavorTextEntries = listOf(
            "When several of these Pokémon gather, their electricity could build and cause lightning storms.",
            "It keeps its tail raised to monitor its surroundings. If you yank its tail, it will try to bite you."
        )
    )
    PokemonDetailScreen(
        dummyPokemonDetail
    )
}

@Composable
fun PokemonDetailScreen(
    pokemon: PokemonDetailUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "#${pokemon.id} ${pokemon.name.capitalize()}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(16.dp))
        TypeRow(types = pokemon.types)

        Spacer(modifier = Modifier.height(16.dp))
        HeightWeightSection(height = pokemon.height, weight = pokemon.weight)

        Spacer(modifier = Modifier.height(16.dp))
        StatSection(stats = pokemon.baseStats)

        Spacer(modifier = Modifier.height(16.dp))
        FlavorTextSection(entries = pokemon.flavorTextEntries)
    }
}

@Composable
fun HeightWeightSection(height: Float, weight: Float) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Height", style = MaterialTheme.typography.labelMedium)
            Text("${height}m", style = MaterialTheme.typography.bodyLarge)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Weight", style = MaterialTheme.typography.labelMedium)
            Text("${weight}kg", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun StatSection(stats: List<Stat>) {
    Column {
        Text("Base Stats", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        stats.forEach { stat ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(stat.name, modifier = Modifier.width(80.dp))
                LinearProgressIndicator(
                    progress = stat.value / 150f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
        }
    }
}

@Composable
fun FlavorTextSection(entries: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text("Flavor Text", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        if (entries.isNotEmpty()) {
            val text = entries.first()
            Text(
                text = if (expanded) text else text.take(140) + "...",
                style = MaterialTheme.typography.bodyMedium
            )
            TextButton(onClick = { expanded = !expanded }) {
                Text(if (expanded) "Show less" else "Read more")
            }
        } else {
            Text("No flavor text available.")
        }
    }
}

//TODO - combine with list type row probably
@Composable
fun TypeRow(types: List<Type>) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        types.forEach { type ->
            Text(
                text = type.name,
                color = Color.White,
                modifier = Modifier
                    .background(type.getTypeColor(), shape = RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        }
    }
}