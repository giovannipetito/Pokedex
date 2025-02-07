package com.satispay.pokedex.presentation.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.R
import com.satispay.pokedex.domain.entity.PokemonEntity
import com.satispay.pokedex.presentation.viewmodel.MainViewModel
import com.satispay.pokedex.ui.items.cards.RoomPokemonCard
import com.satispay.pokedex.utils.Globals.getContentPadding

@Composable
fun FavoritesScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {

    val topics: List<String> = listOf(
        "Room database",
        "CRUD operations",
        "Entity", "DAO",
        "Dependency Injection",
        "Coroutines"
    )

    BaseScreen(
        navController = navController,
        title = stringResource(id = R.string.favorites_screen_title),
        topics = topics
    ) { paddingValues ->

        LaunchedEffect(Unit) {
            mainViewModel.readPokemons()
        }

        val roomPokemons: List<PokemonEntity> by mainViewModel.roomPokemons.collectAsState()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            // verticalArrangement = Arrangement.SpaceEvenly,
            // horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = getContentPadding(paddingValues = paddingValues)
        ) {
            items(roomPokemons) { pokemon ->
                RoomPokemonCard(pokemon = pokemon, modifier = Modifier)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen(navController = rememberNavController(), mainViewModel = hiltViewModel())
}