package com.satispay.pokedex.ui.items.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.satispay.pokedex.data.model.NameUrl
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.data.model.Sprites
import com.satispay.pokedex.data.model.TypeSlot
import com.satispay.pokedex.R

@Composable
fun PokemonCard(
    pokemonDetail: PokemonDetail?,
    isSearch: Boolean = false,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit,
    modifier: Modifier) {
    Column {
        Row {
            AsyncImage(
                modifier = modifier
                    .weight(1f)
                    .padding(all = 4.dp)
                    .align(alignment = Alignment.CenterVertically),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = pokemonDetail?.sprites?.frontDefault)
                    .crossfade(true)
                    .placeholder(R.drawable.ico_loading)
                    .error(R.drawable.ico_pokemon_error)
                    .build(),
                contentDescription = "Pokemon Image",
                contentScale = ContentScale.FillWidth
            )

            BoxWithConstraints(
                modifier = modifier
                    .weight(weight = 3f)
                    .padding(vertical = 12.dp)
            ) {
                AdaptiveContent(
                    pokemonDetail = pokemonDetail,
                    isSearch = isSearch,
                    isFavorite = isFavorite,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 16.dp)
            .background(Color.LightGray)
        )
    }
}

@Composable
fun BoxWithConstraintsScope.AdaptiveContent(
    pokemonDetail: PokemonDetail?,
    isSearch: Boolean,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    val flavorText = cleanupFlavorText(
        pokemonDetail?.flavorTextEntries?.firstOrNull()?.flavorText
    )

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = pokemonDetail?.name?.replaceFirstChar { it.uppercase() }.orEmpty(),
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            if (isSearch) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        onFavoriteClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Favorite Icon",
                        tint = if (isFavorite) Color.Yellow else Color.LightGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(height = 4.dp))
        PokemonTypes(types = pokemonDetail?.types ?: emptyList())
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(
            modifier = Modifier.padding(end = 12.dp),
            text = flavorText,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PokemonTypes(types: List<TypeSlot>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach { typeSlot ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = typeSlot.type.name,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

private fun cleanupFlavorText(text: String?): String {
    return text
        ?.replace("\n", " ")
        ?.replace("\r", " ")
        ?.replace("\\b", "")
        ?: ""
}

@Preview(showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(
        pokemonDetail = PokemonDetail(
            id = 25,
            name = "Pikachu",
            species = NameUrl(name="pikachu", url="https://pokeapi.co/api/v2/pokemon-species/25/"),
            sprites = Sprites(
                backDefault="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png",
                backFemale="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/female/25.png",
                backShiny="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/25.png",
                backShinyFemale="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/female/25.png",
                frontDefault="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
                frontFemale="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/female/25.png",
                frontShiny="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/25.png",
                frontShinyFemale="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/female/25.png"
            ),
            types = arrayListOf(TypeSlot(slot=1, type=NameUrl(name="electric", url="https://pokeapi.co/api/v2/type/13/"))),
            flavorTextEntries = emptyList()
        ),
        onFavoriteClick = {},
        modifier = Modifier.background(Color.White)
    )
}