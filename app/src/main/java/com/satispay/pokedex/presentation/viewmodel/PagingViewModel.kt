package com.satispay.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.model.Pokemon
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.domain.PokemonPagingSource
import com.satispay.pokedex.presentation.viewmodel.UIEvent.ShowSuccess
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
    private val dataSource: UsersDataSource
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
}

sealed class UIEvent {
    data class ShowSuccess(val message: String) : UIEvent()
    data class ShowError(val message: String) : UIEvent()
}