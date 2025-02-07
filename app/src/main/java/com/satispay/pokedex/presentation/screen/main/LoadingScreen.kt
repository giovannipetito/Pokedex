package com.satispay.pokedex.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.presentation.viewmodel.LoadingViewModel
import com.satispay.pokedex.ui.items.LoadingCircles
import kotlinx.coroutines.delay
import com.satispay.pokedex.R

@Composable
fun LoadingScreen(
    navController: NavController,
    viewModel: LoadingViewModel = viewModel(),
    onSplashLoaded: () -> Unit
) {
    viewModel.KeepOrientationPortrait()

    var splashLoading by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = Unit) {
        delay(2000)
        splashLoading = false
        onSplashLoaded()
    }

    if (!splashLoading) {
        LaunchedEffect(key1 = true) {
            delay(2000)
            navController.popBackStack()
            navController.navigate(route = MainRoutes.Home.route) {
                popUpTo(route = MainRoutes.Home.route)
            }
        }
        LoadingScreenContent()
    }
}

@Composable
fun LoadingScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        LoadingCircles(
            circleImages = listOf(
                R.drawable.ico_bulbasaur,
                R.drawable.ico_charmander,
                R.drawable.ico_squirtle
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(
        navController = rememberNavController(),
        viewModel = viewModel(),
        onSplashLoaded = {}
    )
}