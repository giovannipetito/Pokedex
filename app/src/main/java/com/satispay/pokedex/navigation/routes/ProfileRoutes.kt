package com.satispay.pokedex.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class ProfileRoutes {
    @Serializable
    data object Pokedex: ProfileRoutes()
}