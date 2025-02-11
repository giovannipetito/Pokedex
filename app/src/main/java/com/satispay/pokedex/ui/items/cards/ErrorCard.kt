package com.satispay.pokedex.ui.items.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorCard(
    errorMessage: String,
    onRetry: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Red),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRetry() }
    ) {
        Text(
            text = "Error loading more data: $errorMessage\nTap to retry.",
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}