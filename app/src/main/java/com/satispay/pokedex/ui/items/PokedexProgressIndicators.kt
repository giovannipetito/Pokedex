package com.satispay.pokedex.ui.items

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PokedexProgressIndicator(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 4.dp
) {
    CircularProgressIndicator(
        modifier = modifier.size(size = 64.dp),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
        strokeWidth = strokeWidth
    )
}

@Composable
@Preview(showBackground = true)
fun PokedexProgressIndicatorPreview() {
    PokedexProgressIndicator()
}