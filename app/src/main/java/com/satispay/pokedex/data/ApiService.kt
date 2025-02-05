package com.satispay.pokedex.data

import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.data.model.PokemonSpecies
import com.satispay.pokedex.data.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponse

    @GET
    suspend fun getPokemonDetail(@Url url: String): PokemonDetail

    @GET
    suspend fun getPokemonSpecies(@Url url: String): PokemonSpecies
}