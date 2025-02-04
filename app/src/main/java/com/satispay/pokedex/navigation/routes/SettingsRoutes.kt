package com.satispay.pokedex.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsRoutes {
    @Serializable
    data object Favorites: SettingsRoutes()
}