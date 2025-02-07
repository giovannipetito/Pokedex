package com.satispay.pokedex.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeRoutes {
    @Serializable
    data object Pokedex: HomeRoutes()
}