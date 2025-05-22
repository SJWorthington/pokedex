package com.example.anotherpokedex.ui.generalcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.anotherpokedex.domain.model.Type
import com.example.anotherpokedex.ui.utils.getTypeColor

@Composable
fun PokemonListTypePill(modifier: Modifier, type: Type) {
    val backgroundColor = type.getTypeColor()

    Box(
        modifier = modifier.then(
            Modifier
                .background(color = backgroundColor, shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        )
    ) {
        Text(
            text = type.name,
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
