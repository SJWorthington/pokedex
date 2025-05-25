package com.example.anotherpokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.anotherpokedex.ui.navigation.AppNavGraph
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonList
import com.example.anotherpokedex.ui.theme.AnotherPokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotherPokedexTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnotherPokedexTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PokemonList(
                modifier = Modifier.padding(paddingValues = innerPadding),
                navigateToPokemonDetails = {}
            )
        }
    }
}
