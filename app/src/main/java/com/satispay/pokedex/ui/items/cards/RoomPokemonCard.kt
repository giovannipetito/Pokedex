package com.satispay.pokedex.ui.items.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.satispay.pokedex.domain.entity.PokemonEntity

@Composable
fun RoomPokemonCard(pokemon: PokemonEntity, modifier: Modifier) {

    val avatar: AsyncImagePainter = rememberAsyncImagePainter(model = pokemon.imageUrl)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(horizontal = 48.dp, vertical = 12.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = avatar,
                contentDescription = null,
                modifier = Modifier
                    .width(width = 200.dp)
                    .height(height = 200.dp),
                contentScale = ContentScale.FillBounds
            )

            Surface(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(text = pokemon.name?.replaceFirstChar { it.uppercase() }.orEmpty())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomPokemonCardPreview() {
    RoomPokemonCard(
        pokemon = PokemonEntity(
            id = 1,
            name = "Bulbasaur",
            description = "First pokemon",
            imageUrl = ""
        ),
        modifier = Modifier
    )
}