package com.satispay.pokedex.presentation.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.ui.items.MainTextButton
import com.satispay.pokedex.utils.Constants.STATUS_BAR_HEIGHT
import com.satispay.pokedex.R
import com.satispay.pokedex.navigation.routes.ProfileRoutes

@Composable
fun ProfileScreen(navController: NavController) {

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle

    val initialScrollIndex = savedStateHandle?.get<Int>("scroll_index") ?: 0
    val initialScrollOffset = savedStateHandle?.get<Int>("scroll_offset") ?: 0

    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialScrollIndex,
        initialFirstVisibleItemScrollOffset = initialScrollOffset
    )

    DisposableEffect(key1 = lazyListState) {
        onDispose {
            savedStateHandle?.set("scroll_index", lazyListState.firstVisibleItemIndex)
            savedStateHandle?.set("scroll_offset", lazyListState.firstVisibleItemScrollOffset)
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = STATUS_BAR_HEIGHT)
    ) {
        item {
            MainTextButton(
                onClick = {
                    navController.navigate(route = ProfileRoutes.Pokedex)
                },
                id = R.string.pokedex_screen_title
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}