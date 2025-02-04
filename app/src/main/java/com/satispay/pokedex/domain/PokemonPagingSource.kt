package com.satispay.pokedex.domain

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.model.Pokemon
import com.satispay.pokedex.data.response.PokemonResponse
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val dataSource: UsersDataSource,
    private val state: AlertBarState
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val currentPage = params.key ?: 1
        return try {
            val response: PokemonResponse = dataSource.getPokemons(currentPage)

            if (response.results.isNotEmpty()) {
                state.addSuccess(success = "Loading successful!")
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (currentPage == 1) null else currentPage.minus(1),
                    nextKey = if (response.results.isEmpty()) null else currentPage.plus(1)
                )
            } else {
                state.addError(Exception("No items found."))
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection: ")
            state.addError(Exception(error.message + exception.localizedMessage))
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            state.addError(Exception(exception.localizedMessage))
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition
    }
}