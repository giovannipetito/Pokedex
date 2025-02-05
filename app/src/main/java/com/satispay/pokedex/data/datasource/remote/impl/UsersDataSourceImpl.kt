package com.satispay.pokedex.data.datasource.remote.impl

import com.satispay.pokedex.data.ApiService
import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.data.response.PokemonResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UsersDataSourceImpl @Inject constructor(
    @Named("baseUrl") private val apiService: ApiService
): UsersDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): PokemonResponse {
        val response: PokemonResponse = apiService.getPokemons(offset, limit)
        return response
    }

    override suspend fun getPokemonDetail(url: String): PokemonDetail {
        return apiService.getPokemonDetail(url)
    }
}