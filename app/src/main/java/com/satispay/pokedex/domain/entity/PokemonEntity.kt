package com.satispay.pokedex.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String?,
    var description: String?,
    var imageUrl: String?
)