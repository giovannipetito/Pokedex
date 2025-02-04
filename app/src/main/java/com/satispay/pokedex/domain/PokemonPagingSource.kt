package com.satispay.pokedex.domain

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.satispay.pokedex.data.datasource.remote.UsersDataSource
import com.satispay.pokedex.data.model.Pokemon
import com.satispay.pokedex.data.response.PokemonResponse
import com.satispay.pokedex.presentation.viewmodel.UIEvent
import com.satispay.pokedex.utils.Config.PAGE_LIMIT
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val dataSource: UsersDataSource,
    private val onEvent: (UIEvent) -> Unit
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val currentOffset = params.key ?: 0
        return try {
            val response: PokemonResponse = dataSource.getPokemons(offset = currentOffset, limit = PAGE_LIMIT)

            // Checking next
            val nextOffset = if (response.next == null) null else currentOffset + PAGE_LIMIT

            // Checking count
            // val nextOffset = if ((currentOffset + PAGE_LIMIT) < response.count) currentOffset + PAGE_LIMIT else null

            if (response.results.isNotEmpty()) {
                if (currentOffset == 0)
                    onEvent(UIEvent.ShowSuccess("Loading successful!"))
            } else {
                onEvent(UIEvent.ShowError("No items found!"))
            }

            LoadResult.Page(
                data = response.results,
                prevKey = if (currentOffset == 0) null else currentOffset - PAGE_LIMIT,
                nextKey = nextOffset
            )
        } catch (exception: IOException) {
            onEvent(UIEvent.ShowError("An error occurred"))
            LoadResult.Error(exception)
        } catch (http: HttpException) {
            onEvent(UIEvent.ShowError(http.localizedMessage ?: "Unknown HTTP error"))
            LoadResult.Error(http)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(PAGE_LIMIT) ?: anchorPage.nextKey?.minus(PAGE_LIMIT)
    }
}