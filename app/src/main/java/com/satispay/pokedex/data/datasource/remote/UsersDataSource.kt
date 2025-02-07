package com.satispay.pokedex.data.datasource.remote

import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.data.model.PokemonSpecies
import com.satispay.pokedex.data.response.PokemonResponse

interface UsersDataSource {
    suspend fun getPokemons(offset: Int, limit: Int): PokemonResponse

    suspend fun getPokemonDetail(url: String): PokemonDetail

    suspend fun getPokemonDetailBySearch(searchUrl: String): PokemonDetail

    suspend fun getPokemonSpecies(url: String): PokemonSpecies
}