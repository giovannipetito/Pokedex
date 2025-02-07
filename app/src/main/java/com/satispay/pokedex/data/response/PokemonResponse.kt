package com.satispay.pokedex.data.response

import com.google.gson.annotations.SerializedName
import com.satispay.pokedex.data.model.Pokemon

data class PokemonResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<Pokemon>
)