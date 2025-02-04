package com.satispay.pokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.model.Pokemon
import com.satispay.pokedex.domain.AlertBarState
import com.satispay.pokedex.domain.PokemonPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val dataSource: UsersDataSource
) : ViewModel() {

    /**
     * Get data with Paging
     */
    fun getDataFlow(state: AlertBarState): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = {
                PokemonPagingSource(dataSource = dataSource, state = state)
            }
        ).flow
    }
}