package com.example.anotherpokedex.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.anotherpokedex.ui.screens.pokemondetail.PokemonDetail
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonListRoute

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "pokemon_list"
    ) {
        composable("pokemon_list") {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                PokemonListRoute(
                    modifier = modifier,
                    navigateToPokemonDetails = { name ->
                        navController.navigate("pokemon_detail/$name")
                    }
                )
            }
        }
        composable(
            route = "pokemon_detail/{pokemonName}",
            arguments = listOf(
                navArgument("pokemonName") { type = NavType.StringType }
            )
        ) {
            PokemonDetail(modifier = modifier)
        }
    }
}