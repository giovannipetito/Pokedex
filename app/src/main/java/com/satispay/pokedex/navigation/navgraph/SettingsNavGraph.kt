package com.satispay.pokedex.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.satispay.pokedex.navigation.Graph
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.navigation.routes.SettingsRoutes
import com.satispay.pokedex.presentation.screen.detail.FavoritesScreen
import com.satispay.pokedex.presentation.screen.main.SettingsScreen

fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.SETTINGS_ROUTE,
        startDestination = MainRoutes.Settings.route
    ) {
        composable(
            route = MainRoutes.Settings.route
        ) {
            SettingsScreen(navController = navController)
        }

        composable<SettingsRoutes.Favorites> {
            FavoritesScreen(navController = navController)
        }
    }
}