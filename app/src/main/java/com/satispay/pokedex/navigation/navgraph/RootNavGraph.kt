package com.satispay.pokedex.navigation.navgraph

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.satispay.pokedex.navigation.Loading
import com.satispay.pokedex.presentation.screen.main.LoadingScreen
import com.satispay.pokedex.presentation.viewmodel.MainViewModel

@Composable
fun RootNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Loading,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(700)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(700)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(700)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(700)) }
    ) {
        composable<Loading> {
            LoadingScreen(
                navController = navController,
                onSplashLoaded = {
                    mainViewModel.setSplashOpened(state = false)
                }
            )
        }

        // Nested Navigation Graphs
        homeNavGraph(
            navController = navController,
            mainViewModel = mainViewModel
        )

        profileNavGraph(
            navController = navController
        )
    }
}