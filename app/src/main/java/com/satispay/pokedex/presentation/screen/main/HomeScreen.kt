package com.satispay.pokedex.presentation.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.R
import com.satispay.pokedex.navigation.routes.HomeRoutes
import com.satispay.pokedex.presentation.viewmodel.MainViewModel
import com.satispay.pokedex.ui.items.MainTextButton
import com.satispay.pokedex.utils.Constants.STATUS_BAR_HEIGHT

@Composable
fun HomeScreen(
    navController: NavController,
    mainViewModel: MainViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = STATUS_BAR_HEIGHT)
    ) {
        item {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.logo_pokemon),
                contentDescription = "Poke ball Image"
            )
        }
        item {
            MainTextButton(
                onClick = {
                    navController.navigate(route = HomeRoutes.Pokedex)
                },
                id = R.string.open_pokedex_screen
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), mainViewModel = viewModel())
}