package com.satispay.pokedex.presentation.screen.detail

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.satispay.pokedex.R
import com.satispay.pokedex.data.model.Pokemon
import com.satispay.pokedex.domain.AlertBarState
import com.satispay.pokedex.presentation.viewmodel.PagingViewModel
import com.satispay.pokedex.presentation.viewmodel.UIEvent
import com.satispay.pokedex.ui.items.AlertBarContent
import com.satispay.pokedex.ui.items.PokedexProgressIndicator
import com.satispay.pokedex.ui.items.cards.PokemonCard
import com.satispay.pokedex.ui.items.rememberAlertBarState
import com.satispay.pokedex.utils.AlertBarPosition
import com.satispay.pokedex.utils.Globals.getContentPadding
import kotlinx.coroutines.launch

@Composable
fun PokedexScreen(
    navController: NavController,
    viewModel: PagingViewModel = hiltViewModel()
) {
    val topics: List<String> = listOf(
        "hiltViewModel",
        "DataSource",
        "suspend functions",
        "Flow",
        "PagingData"
    )

    val alertBarState: AlertBarState = rememberAlertBarState()

    /*
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is UIEvent.ShowSuccess -> alertBarState.addSuccess(event.message)
                is UIEvent.ShowError   -> alertBarState.addError(Exception(event.message))
            }
        }
    }
    */

    val scope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        val job = scope.launch {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    is UIEvent.ShowSuccess -> alertBarState.addSuccess(event.message)
                    is UIEvent.ShowError   -> alertBarState.addError(Exception(event.message))
                }
            }
        }
        onDispose { job.cancel() }
    }

    BaseScreen(
        navController = navController,
        title = stringResource(id = R.string.pokedex_screen_title),
        topics = topics
    ) { paddingValues ->
        val pokemons: LazyPagingItems<Pokemon> = viewModel.pokemonFlow.collectAsLazyPagingItems()

        AlertBarContent(
            position = AlertBarPosition.BOTTOM,
            alertBarState = alertBarState,
            successMaxLines = 3,
            errorMaxLines = 3
        ) {
            ShowPokemons(pokemons = pokemons, paddingValues = paddingValues)
        }
    }
}

@Composable
fun ShowPokemons(pokemons: LazyPagingItems<Pokemon>, paddingValues: PaddingValues) {

    Log.d("[PAGING]", "Load State:" + pokemons.loadState.toString())

    LazyColumn(
        contentPadding = getContentPadding(paddingValues = paddingValues)
    ) {
        if (pokemons.itemCount == 0) {
            item {
                PokedexProgressIndicator()
            }
        }

        items(
            count = pokemons.itemCount,
            key = pokemons.itemKey { it.url },
            contentType = pokemons.itemContentType { "contentType" }
        ) { index: Int ->
            val pokemon: Pokemon? = pokemons[index]
            Spacer(modifier = Modifier.height(height = 4.dp))
            PokemonCard(pokemon = pokemon, modifier = Modifier)
            Spacer(modifier = Modifier.height(height = 4.dp))
        }

        // Handle loading state // todo: to test.
        pokemons.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    // Show loading at the beginning of the list
                }
                loadState.append is LoadState.Loading -> {
                    // Show loading at the end of the list
                }
                loadState.refresh is LoadState.Error -> {
                    // Handle error at the beginning of the list
                }
                loadState.append is LoadState.Error -> {
                    // Handle error at the end of the list
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexScreenPreview() {
    PokedexScreen(navController = rememberNavController())
}