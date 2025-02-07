package com.satispay.pokedex.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.domain.AlertBarState
import com.satispay.pokedex.domain.entity.PokemonEntity
import com.satispay.pokedex.presentation.viewmodel.MainViewModel
import com.satispay.pokedex.presentation.viewmodel.PagingViewModel
import com.satispay.pokedex.presentation.viewmodel.UIEvent
import com.satispay.pokedex.ui.items.AlertBarContent
import com.satispay.pokedex.ui.items.CustomTitle
import com.satispay.pokedex.ui.items.PokedexProgressIndicator
import com.satispay.pokedex.ui.items.PokedexTextField
import com.satispay.pokedex.ui.items.cards.ErrorCard
import com.satispay.pokedex.ui.items.cards.PokemonCard
import com.satispay.pokedex.ui.items.rememberAlertBarState
import com.satispay.pokedex.utils.AlertBarPosition
import com.satispay.pokedex.utils.Globals.getContentPaddingTop
import kotlinx.coroutines.launch

@Composable
fun PokedexScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
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

        LaunchedEffect(Unit) {
            mainViewModel.readPokemons()
        }

        val pokemonDetails: LazyPagingItems<PokemonDetail> = viewModel.pokemonFlow.collectAsLazyPagingItems()

        val searchedPokemons: List<PokemonDetail> = viewModel.searchedPokemons.collectAsState().value

        val searchText: String = viewModel.searchText.collectAsState().value

        val roomPokemons: List<PokemonEntity> by mainViewModel.roomPokemons.collectAsState()

        AlertBarContent(
            position = AlertBarPosition.BOTTOM,
            alertBarState = alertBarState,
            successMaxLines = 3,
            errorMaxLines = 3
        ) {
            if (searchedPokemons.isNotEmpty()) {
                ShowSearchedPokemons(
                    paddingValues = paddingValues,
                    searchedPokemons = searchedPokemons,
                    roomPokemons = roomPokemons,
                    mainViewModel = mainViewModel
                )
            } else {
                ShowPokemons(
                    paddingValues = paddingValues,
                    pokemonDetails = pokemonDetails,
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(top = 24.dp)
                ) {
                    CustomTitle(color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(24.dp))
                    PokedexTextField(
                        text = searchText,
                        placeholder = "Search name or type",
                        onTextChange = { newValue ->
                            viewModel.onSearchTextChanged(newValue)
                        },
                        onSearchClicked = {
                            viewModel.onSearchClicked()
                        },
                        onCloseClicked = {
                            viewModel.onClearSearch()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ShowPokemons(
    paddingValues: PaddingValues,
    pokemonDetails: LazyPagingItems<PokemonDetail>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 64.dp),
        contentPadding = getContentPaddingTop(paddingValues = paddingValues)
    ) {
        if (pokemonDetails.itemCount == 0) {
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    PokedexProgressIndicator()
                }
            }
        }

        items(
            count = pokemonDetails.itemCount,
            key = pokemonDetails.itemKey { it.id.toString() }, // key = { index -> pokemonDetails[index]?.id.toString() }
            contentType = pokemonDetails.itemContentType { "contentType" }
        ) { index: Int ->
            val pokemonDetail: PokemonDetail? = pokemonDetails[index]
            Spacer(modifier = Modifier.height(height = 4.dp))
            PokemonCard(pokemonDetail = pokemonDetail, modifier = Modifier, onFavoriteClick = {})
            Spacer(modifier = Modifier.height(height = 4.dp))
        }

        // Handle loading state
        when (val appendState = pokemonDetails.loadState.append) { // .refresh: show loading at the beginning of the list
            is LoadState.Loading -> {
                item {
                    // Show loading at the end of the list
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PokedexProgressIndicator()
                    }
                }
            }
            is LoadState.Error -> {
                item {
                    // Show error item at the end of the list
                    ErrorCard(
                        errorMessage = appendState.error.localizedMessage ?: "Unknown error",
                        onRetry = { pokemonDetails.retry() }
                    )
                }
            }
            else -> {
                // Do nothing
            }
        }
    }
}

@Composable
fun ShowSearchedPokemons(
    paddingValues: PaddingValues,
    searchedPokemons: List<PokemonDetail>,
    roomPokemons: List<PokemonEntity>,
    mainViewModel: MainViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        contentPadding = getContentPaddingTop(paddingValues = paddingValues)
    ) {
        items(
            items = searchedPokemons,
            key = { it.id }
        ) { pokemonDetail ->

            val isFavorite = roomPokemons.any { it.id == pokemonDetail.id }

            Spacer(modifier = Modifier.height(4.dp))

            PokemonCard(
                pokemonDetail = pokemonDetail,
                modifier = Modifier,
                isSearch = true,
                isFavorite = isFavorite,
                onFavoriteClick = {
                    if (isFavorite) {
                        pokemonDetail.let {
                            mainViewModel.deletePokemon(
                                pokemonEntity = PokemonEntity(
                                    id = pokemonDetail.id,
                                    name = pokemonDetail.name,
                                    description = "",
                                    imageUrl = pokemonDetail.sprites.frontDefault
                                )
                            )
                        }
                    } else {
                        pokemonDetail.let {
                            mainViewModel.createPokemon(
                                pokemonEntity = PokemonEntity(
                                    id = pokemonDetail.id,
                                    name = pokemonDetail.name,
                                    description = "",
                                    imageUrl = pokemonDetail.sprites.frontDefault
                                )
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokedexScreenPreview() {
    PokedexScreen(navController = rememberNavController(), mainViewModel = hiltViewModel())
}