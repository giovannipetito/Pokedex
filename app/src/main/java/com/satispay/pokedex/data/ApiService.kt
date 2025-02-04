package com.satispay.pokedex.data

import com.satispay.pokedex.data.response.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("page") page: Int
    ): PokemonResponse
}