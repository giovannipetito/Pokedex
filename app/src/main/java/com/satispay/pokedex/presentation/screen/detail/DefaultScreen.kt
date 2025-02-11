package com.satispay.pokedex.presentation.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.R
import com.satispay.pokedex.utils.Globals.getContentPadding

@Composable
fun DefaultScreen(navController: NavController) {

    val topics: List<String> = listOf("")

    BaseScreen(
        navController = navController,
        title = stringResource(id = R.string.app_name),
        topics = topics
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = getContentPadding(paddingValues = paddingValues)
        ) {
            item {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultScreenPreview() {
    DefaultScreen(navController = rememberNavController())
}