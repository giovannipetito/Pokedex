package com.satispay.pokedex.data.datasource.remote.impl

import com.satispay.pokedex.data.ApiService
import com.satispay.pokedex.data.datasource.remote.PokemonDataSource
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.data.model.PokemonSpecies
import com.satispay.pokedex.data.response.PokemonResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class PokemonDataSourceImpl @Inject constructor(
    @Named("baseUrl") private val apiService: ApiService
): PokemonDataSource {

    override suspend fun getPokemons(offset: Int, limit: Int): PokemonResponse {
        val response: PokemonResponse = apiService.getPokemons(offset, limit)
        return response
    }

    override suspend fun getPokemonDetail(url: String): PokemonDetail {
        return apiService.getPokemonDetail(url)
    }

    override suspend fun getPokemonDetailBySearch(searchUrl: String): PokemonDetail {
        return apiService.getPokemonDetail(searchUrl)
    }

    override suspend fun getPokemonSpecies(url: String): PokemonSpecies {
        return apiService.getPokemonSpecies(url)
    }
}