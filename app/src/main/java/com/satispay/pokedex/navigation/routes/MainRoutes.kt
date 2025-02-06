package com.satispay.pokedex.navigation.routes

import com.satispay.pokedex.R

enum class MainRoutes(
    val route: String,
    val label: String,
    val selectedIconRes: Int,
    val unselectedIconRes: Int
) {
    Home(
        route = "home_screen",
        label = "Home",
        selectedIconRes = R.drawable.ico_pokedex_on,
        unselectedIconRes = R.drawable.ico_pokedex_off
    ),
    Profile(
        route = "profile_screen",
        label = "Profile",
        selectedIconRes = R.drawable.ico_favorites_on,
        unselectedIconRes = R.drawable.ico_favorites_off
    )
}