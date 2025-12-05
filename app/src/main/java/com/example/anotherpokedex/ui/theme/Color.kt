package com.example.anotherpokedex.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// --------------------------------------------------------------------
//  Material's lame baseline colors
// --------------------------------------------------------------------
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


// --------------------------------------------------------------------
//  MY RAD POKÉMON THEME COLORS (UI personality layer)
// --------------------------------------------------------------------
@Immutable
data class PokeColors(
    val pokeballRed: Color,
    val pokeballYellow: Color,
    val ceruleanBlue: Color,
    val skyBackground: Color,
    val disabledBorder: Color, //TODO - better name
)

val LightPokeColors = PokeColors(
    pokeballRed = Color(0xFFEE2E2E),
    pokeballYellow = Color(0xFFFFD93D),
    ceruleanBlue = Color(0xFF355B8C),
    skyBackground = Color(0xFFE6F4FF),
    disabledBorder = Color.White.copy(alpha = 0.4f)
)

val DarkPokeColors = PokeColors(
    pokeballRed = Color(0xFFCC2A2A),
    pokeballYellow = Color(0xFFE6C237),
    ceruleanBlue = Color(0xFFAFCBFF),
    skyBackground = Color(0xFF1B263B),
    disabledBorder = Color.White.copy(alpha = 0.25f)
)

val LocalPokeColors = staticCompositionLocalOf { LightPokeColors }


// --------------------------------------------------------------------
//  POKÉMON TYPE COLORS (for chips, backgrounds, badges, etc.)
// --------------------------------------------------------------------
@Immutable
data class TypeColors(
    val fire: Color,
    val water: Color,
    val grass: Color,
    val electric: Color,
    val normal: Color,
    val ice: Color,
    val fighting: Color,
    val poison: Color,
    val ground: Color,
    val flying: Color,
    val psychic: Color,
    val bug: Color,
    val rock: Color,
    val ghost: Color,
    val dragon: Color,
    val dark: Color,
    val steel: Color,
    val fairy: Color,
)

val LightTypeColors = TypeColors(
    fire = Color(0xFFD97638),
    water = Color(0xFF3399FF),
    grass = Color(0xFF78C850),
    electric = Color(0xFFF8D030),
    normal = Color(0xFFA8A878),
    ice = Color(0xFF98D8D8),
    fighting = Color(0xFFC03028),
    poison = Color(0xFFA040A0),
    ground = Color(0xFFE0C068),
    flying = Color(0xFFA890F0),
    psychic = Color(0xFFF85888),
    bug = Color(0xFFA8B820),
    rock = Color(0xFFB8A038),
    ghost = Color(0xFF705898),
    dragon = Color(0xFF7038F8),
    dark = Color(0xFF705848),
    steel = Color(0xFFB8B8D0),
    fairy = Color(0xFFF0B6BC)
)

// Optional: tweak a few for dark theme
val DarkTypeColors = LightTypeColors.copy(
    normal = Color(0xFFCCCBB3),
    rock = Color(0xFFD0BE6E),
    ghost = Color(0xFF9A84C8),
)

val LocalTypeColors = staticCompositionLocalOf { LightTypeColors }

