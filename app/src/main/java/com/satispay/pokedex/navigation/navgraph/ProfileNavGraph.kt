package com.satispay.pokedex.navigation.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.satispay.pokedex.navigation.Graph
import com.satispay.pokedex.navigation.routes.MainRoutes
import com.satispay.pokedex.navigation.routes.ProfileRoutes
import com.satispay.pokedex.presentation.screen.detail.FavoritesScreen
import com.satispay.pokedex.presentation.screen.main.ProfileScreen

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.PROFILE_ROUTE,
        startDestination = MainRoutes.Profile.route
    ) {
        composable(
            route = MainRoutes.Profile.route
        ) {
            ProfileScreen(navController = navController)
        }

        composable<ProfileRoutes.Favorites> {
            FavoritesScreen(navController = navController)
        }
    }
}