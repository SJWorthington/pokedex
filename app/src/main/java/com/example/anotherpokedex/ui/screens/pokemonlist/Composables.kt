package com.example.anotherpokedex.ui.screens.pokemonlist

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.anotherpokedex.R
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.screens.pokemonlist.models.PokemonUiModel
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonGridItem(
    modifier: Modifier = Modifier,
    pokemon: PokemonUiModel,
    backgroundBrush: Brush,
    onClick: (Int) -> Unit,
    onClickFavourite: (Int) -> Unit,
    onClickUnfavourite: (Int) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(brush = backgroundBrush, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(pokemon.id) }
    ) {
        if (pokemon.displayShiny) {
            PokemonGlowOverlay(modifier = Modifier.matchParentSize())
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(pokemon.imageUri)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                placeholder = painterResource(R.drawable.who_is_that_pokemon),
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pokemon.readableNumber, style = MaterialTheme.typography.bodySmall)
            Text(text = pokemon.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            PokemonListTypeRow(pokemon)
        }

        PokemonListFavouriteIcon(
            isFavourite = pokemon.isFavourite,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(36.dp)
                .clickable {
                    when (pokemon.isFavourite) {
                        true -> onClickUnfavourite(pokemon.id)
                        false -> onClickFavourite(pokemon.id)
                    }
                }
        )
    }
}

@Composable
fun PokemonListTypePill(modifier: Modifier, type: Type) {
    val backgroundColor = type.getTypeColor()

    Box(
        modifier = modifier
            .shadow(2.dp, shape = RoundedCornerShape(50))
            .background(color = backgroundColor, shape = RoundedCornerShape(50))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.6f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = type.name,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun PokemonListTypeRow(pokemon: PokemonUiModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.wrapContentWidth()
    ) {
        PokemonListTypePill(
            modifier = Modifier.padding(end = 4.dp),
            type = pokemon.types.first
        )
        pokemon.types.second?.let {
            PokemonListTypePill(
                modifier = Modifier.padding(end = 4.dp),
                type = it
            )
        }
    }
}

@Composable
fun PokemonGlowOverlay(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "shiny-glow")
    val glowAlpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Box(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Blue.copy(alpha = glowAlpha * 0.8f),
                    Color.Blue.copy(alpha = glowAlpha),
                    Color.Blue.copy(alpha = glowAlpha * 0.8f),
                    Color.Transparent
                )
            ),
            shape = RoundedCornerShape(16.dp)
        )
    )
}

@Composable
fun PokemonListLoadStateItem(
    loadState: LoadState,
    errorPrefix: String = "Something went wrong"
) {
    when (loadState) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> {
            Text(
                text = "$errorPrefix: ${loadState.error.localizedMessage}",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        else -> Unit
    }
}

@Composable
fun PokemonListFavouriteIcon(
    isFavourite: Boolean,
    modifier: Modifier = Modifier
) {
    var previous by remember { mutableStateOf(isFavourite) }
    var animatePop by remember { mutableStateOf(false) }

    LaunchedEffect(isFavourite) {
        if (!previous && isFavourite) animatePop = true
        previous = isFavourite
    }

    val scale by animateFloatAsState(
        targetValue = if (animatePop) 1.3f else 1f,
        animationSpec = tween(200),
        finishedListener = { animatePop = false }
    )

    Image(
        painter = painterResource(
            if (isFavourite) R.drawable.ic_favourite else R.drawable.ic_not_favourite
        ),
        modifier = modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
        },
        contentDescription = null
    )
}