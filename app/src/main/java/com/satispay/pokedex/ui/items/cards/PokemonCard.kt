package com.satispay.pokedex.ui.items.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satispay.pokedex.data.model.Pokemon

@Composable
fun PokemonCard(pokemon: Pokemon?, modifier: Modifier) {

    // val avatar: AsyncImagePainter = rememberAsyncImagePainter(model = pokemon.image)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            /*
            Image(
                painter = avatar,
                contentDescription = null,
                modifier = Modifier
                    .width(width = 300.dp)
                    .height(height = 300.dp),
                contentScale = ContentScale.FillBounds
            )
            */

            Surface(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 4.dp)
                ) {
                    Text(text = "Name: " + pokemon?.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(
        pokemon = Pokemon(
            name = "Pikachu",
            url = "",
        ),
        modifier = Modifier
    )
}