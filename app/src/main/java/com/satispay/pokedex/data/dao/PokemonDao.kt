package com.satispay.pokedex.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.satispay.pokedex.domain.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createPokemon(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM pokemon_table ORDER BY id ASC")
    suspend fun readPokemons(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon_table WHERE id = :id")
    suspend fun readPokemonById(id: Int): PokemonEntity

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updatePokemon(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM pokemon_table")
    suspend fun deletePokemons()

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)
}