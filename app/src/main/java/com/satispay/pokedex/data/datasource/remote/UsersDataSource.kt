package com.satispay.pokedex.data.datasource.remote

import com.satispay.pokedex.data.response.PokemonResponse

interface UsersDataSource {
    suspend fun getPokemons(page: Int): PokemonResponse
}