package com.satispay.pokedex.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.satispay.pokedex.navigation.Graph.HOME_ROUTE
import com.satispay.pokedex.navigation.routes.HomeRoutes
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.presentation.screen.detail.PokedexScreen
import com.satispay.pokedex.presentation.screen.main.HomeScreen
import com.satispay.pokedex.presentation.viewmodel.MainViewModel

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    navigation(
        route = HOME_ROUTE,
        startDestination = MainRoutes.Home.route
    ) {
        composable(
            route = MainRoutes.Home.route
        ) {
            HomeScreen(navController = navController)
        }

        composable<HomeRoutes.Pokedex> {
            PokedexScreen(navController = navController, mainViewModel = mainViewModel)
        }
    }
}