package com.example.anotherpokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.anotherpokedex.ui.screens.pokemondetail.PokemonDetail
import com.example.anotherpokedex.ui.screens.pokemonlist.PokemonList

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
            PokemonList(
                modifier = modifier,
                navigateToPokemonDetails = { id ->
                    navController.navigate("pokemon_detail/$id")
                }
            )
        }
        composable(
            route = "pokemon_detail/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") { type = NavType.IntType }
            )
        ) {
            PokemonDetail(modifier = modifier)
        }
    }
}