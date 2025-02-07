package com.satispay.pokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.satispay.pokedex.data.dao.PokemonDao
import com.satispay.pokedex.domain.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = true)
abstract class PokedexRoomDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}