package com.satispay.pokedex.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satispay.pokedex.data.datasource.local.RoomRepository
import com.satispay.pokedex.domain.entity.PokemonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    private val _keepSplashOpened: MutableState<Boolean> = mutableStateOf(false)
    var keepSplashOpened: State<Boolean> = _keepSplashOpened

    private val _firstAccess: MutableState<Boolean> = mutableStateOf(false)
    var firstAccess: State<Boolean> = _firstAccess

    fun setSplashOpened(state: Boolean) {
        _keepSplashOpened.value = state
    }

    fun setFirstAccess(firstAccess: Boolean) {
        _firstAccess.value = firstAccess
    }

    // Room database
    private val _roomPokemons: MutableStateFlow<List<PokemonEntity>> = MutableStateFlow(emptyList())
    val roomPokemons: StateFlow<List<PokemonEntity>> = _roomPokemons.asStateFlow()

    private var _roomPokemonById: MutableState<PokemonEntity>? = mutableStateOf(PokemonEntity(0, "", "", ""))
    val roomPokemonById: State<PokemonEntity>? = _roomPokemonById

    init {
        // readPokemons()
    }

    fun createPokemon(pokemonEntity: PokemonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createPokemon(pokemonEntity = pokemonEntity)
            readPokemons() // Needed to update the UI.
        }
    }

    fun readPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            _roomPokemons.value = repository.readPokemons()
        }
    }

    fun readPokemonById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _roomPokemonById?.value = repository.readPokemonById(id = id)
            readPokemons() // Needed to update the UI.
        }
    }

    fun updatePokemon(pokemonEntity: PokemonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePokemon(pokemonEntity = pokemonEntity)
            readPokemons() // Needed to update the UI.
        }
    }

    fun deletePokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePokemons()
            readPokemons() // Needed to update the UI.
        }
    }

    fun deletePokemon(pokemonEntity: PokemonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePokemon(pokemonEntity = pokemonEntity)
            readPokemons() // Needed to update the UI.
        }
    }
}