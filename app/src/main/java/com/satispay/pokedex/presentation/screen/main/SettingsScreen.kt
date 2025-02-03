package com.satispay.pokedex.presentation.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.ui.items.MainTextButton
import com.satispay.pokedex.utils.Constants.STATUS_BAR_HEIGHT
import com.satispay.pokedex.R

@Composable
fun SettingsScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(top = STATUS_BAR_HEIGHT)
    ) {
        /*
        item {
            MainTextButton(
                onClick = {
                    navController.navigate(route = SettingsRoutes.Colors)
                },
                id = R.string.colors
            )
        }
        item {
            MainTextButton(
                onClick = {
                    navController.navigate(route = SettingsRoutes.Fonts)
                },
                id = R.string.fonts
            )
        }
        */
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}