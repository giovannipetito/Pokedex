package com.satispay.pokedex.data.datasource.remote.impl

import com.satispay.pokedex.data.ApiService
import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.response.PokemonResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UsersDataSourceImpl @Inject constructor(
    @Named("baseUrl") private val apiService: ApiService
): UsersDataSource {

    override suspend fun getPokemons(page: Int): PokemonResponse {
        val response: PokemonResponse = apiService.getPokemons(page)
        return response
    }
}