package com.satispay.pokedex.data.datasource.local

import com.satispay.pokedex.data.dao.PokemonDao
import com.satispay.pokedex.domain.entity.PokemonEntity

class RoomRepository(private val pokemonDao: PokemonDao) {

    suspend fun createPokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.createPokemon(pokemonEntity = pokemonEntity)
    }

    suspend fun readPokemons(): List<PokemonEntity> {
        return pokemonDao.readPokemons()
    }

    suspend fun readPokemonById(id: Int): PokemonEntity {
        return pokemonDao.readPokemonById(id = id)
    }

    suspend fun updatePokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.updatePokemon(pokemonEntity = pokemonEntity)
    }

    suspend fun deletePokemons() {
        pokemonDao.deletePokemons()
    }

    suspend fun deletePokemon(pokemonEntity: PokemonEntity) {
        pokemonDao.deletePokemon(pokemonEntity = pokemonEntity)
    }
}