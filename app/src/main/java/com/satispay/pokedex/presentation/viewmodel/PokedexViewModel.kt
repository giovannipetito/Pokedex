package com.satispay.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.satispay.pokedex.data.datasource.remote.PokemonDataSource
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.domain.PokemonPagingSource
import com.satispay.pokedex.utils.Config.BASE_URL
import com.satispay.pokedex.utils.Config.PAGE_LIMIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val dataSource: PokemonDataSource
) : ViewModel() {

    private val _uiEvents = MutableSharedFlow<UIEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    // private val _uiEvents: MutableStateFlow<UIEvent> = MutableStateFlow(ShowSuccess(""))
    // val uiEvents: StateFlow<UIEvent> = _uiEvents.asStateFlow()

    private val pager: Pager<Int, PokemonDetail> by lazy {
        Pager(
            config = PagingConfig(pageSize = PAGE_LIMIT),
            pagingSourceFactory = {
                PokemonPagingSource(dataSource = dataSource) { event ->
                    viewModelScope.launch {
                        _uiEvents.emit(event)
                    }
                }
            }
        )
    }

    val pokemonFlow: Flow<PagingData<PokemonDetail>> = pager.flow.cachedIn(viewModelScope)

    // Search feature:

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _searchedPokemons: MutableStateFlow<List<PokemonDetail>> = MutableStateFlow(emptyList())
    val searchedPokemons: StateFlow<List<PokemonDetail>> = _searchedPokemons.asStateFlow()

    fun onSearchTextChanged(newText: String) {
        _searchText.value = newText
    }

    fun onClearSearch() {
        _searchText.value = ""
        _searchedPokemons.value = emptyList()
    }

    fun onSearchClicked() {
        val query = _searchText.value.trim()
        if (query.isEmpty()) return

        viewModelScope.launch {
            try {
                val pokemonDetail = dataSource.getPokemonDetailBySearch(BASE_URL + "pokemon/${query.lowercase()}")
                val species = dataSource.getPokemonSpecies(pokemonDetail.species.url)
                pokemonDetail.copy(flavorTextEntries = species.flavorTextEntries)
                _searchedPokemons.value = listOf(pokemonDetail)
            } catch (e: Exception) {
                _uiEvents.emit(UIEvent.ShowError("Could not find Pokémon: $query"))
            }
        }
    }
}

sealed class UIEvent {
    data class ShowSuccess(val message: String) : UIEvent()
    data class ShowError(val message: String) : UIEvent()
}