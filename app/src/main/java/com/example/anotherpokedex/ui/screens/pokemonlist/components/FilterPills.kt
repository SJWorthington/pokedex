package com.example.anotherpokedex.ui.screens.pokemonlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.anotherpokedex.R
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FavouriteFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.FiltersUiModel
import com.example.anotherpokedex.ui.screens.pokemonlist.models.GenerationFilter
import com.example.anotherpokedex.ui.screens.pokemonlist.models.SampleData
import com.example.anotherpokedex.ui.screens.pokemonlist.models.TypeFilter
import com.example.anotherpokedex.ui.theme.AnotherPokedexTheme
import com.example.anotherpokedex.ui.theme.pokeColors

@Composable
fun FilterPill(
    modifier: Modifier = Modifier,
    text: String,
    isDropdown: Boolean,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val colours = MaterialTheme.pokeColors

    val bgColor = if (isEnabled) colours.pokeballRed else colours.skyBackground
    val borderColor = if (isEnabled) colours.pokeballYellow else colours.disabledBorder
    val textColor = if (isEnabled) Color.White else colours.ceruleanBlue

    Row(
        modifier = modifier
            .clickable { onClick.invoke() }
            .clip(RoundedCornerShape(50))
            .background(bgColor)
            .border(
                width = if (isEnabled) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(50)
            )
            .shadow(
                elevation = if (isEnabled) 6.dp else 0.dp,
                shape = RoundedCornerShape(50),
                clip = false
            )
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = if (isEnabled) FontWeight.Bold else FontWeight.Medium
            )
        )

        if (isDropdown) {
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                contentDescription = "Filter pill drop down icon",
                tint = textColor.copy(alpha = 0.8f),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun FiltersRow(
    filters: List<FiltersUiModel>,
    onClickFavouritesFilter: () -> Unit,
    onClickTypesFilter: () -> Unit,
    onClickGensFilter: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        filters.forEach { filter ->
            FilterPill(
                text = filter.displayText,
                isDropdown = filter.subFilterOptions != null,
                isEnabled = filter.filter.isActive,
                onClick = {
                    when (filter.filter) {
                        is FavouriteFilter -> onClickFavouritesFilter.invoke()
                        is TypeFilter -> onClickTypesFilter.invoke()
                        is GenerationFilter -> onClickGensFilter.invoke()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterRowPreview() {
    AnotherPokedexTheme {
        FiltersRow(
            filters = SampleData.sampleFilters,
            onClickFavouritesFilter = {},
            onClickTypesFilter = {},
            onClickGensFilter = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterPillPreview() {
    AnotherPokedexTheme {
        FilterPill(
            text = "Favourites",
            isDropdown = true,
            isEnabled = true,
            onClick = {}
        )
    }
}