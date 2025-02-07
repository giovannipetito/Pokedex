package com.satispay.pokedex.domain

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState
import com.satispay.pokedex.data.datasource.remote.PokemonDataSource
import com.satispay.pokedex.data.model.Pokemon
import com.satispay.pokedex.data.model.PokemonDetail
import com.satispay.pokedex.data.response.PokemonResponse
import com.satispay.pokedex.presentation.viewmodel.UIEvent
import com.satispay.pokedex.utils.Config.PAGE_LIMIT
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(
    private val dataSource: PokemonDataSource,
    private val onEvent: (UIEvent) -> Unit
) : PagingSource<Int, PokemonDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDetail> {
        val currentOffset = params.key ?: 0
        return try {
            val response: PokemonResponse = dataSource.getPokemons(offset = currentOffset, limit = PAGE_LIMIT)
            val pokemons: List<Pokemon> = response.results

            val pokemonDetails: List<PokemonDetail> = pokemons.map { pokemon ->

                val pokemonDetail = dataSource.getPokemonDetail(pokemon.url)

                val species = dataSource.getPokemonSpecies(pokemonDetail.species.url)

                pokemonDetail.copy(flavorTextEntries = species.flavorTextEntries)
            }

            // Checking next
            val nextOffset = if (response.next == null) null else currentOffset + PAGE_LIMIT

            // Checking count
            // val nextOffset = if ((currentOffset + PAGE_LIMIT) < response.count) currentOffset + PAGE_LIMIT else null

            if (pokemonDetails.isNotEmpty()) {
                if (currentOffset == 0)
                    onEvent(UIEvent.ShowSuccess("Loading successful!"))
            } else {
                onEvent(UIEvent.ShowError("No items found!"))
            }

            LoadResult.Page(
                data = pokemonDetails,
                prevKey = if (currentOffset == 0) null else currentOffset - PAGE_LIMIT,
                nextKey = nextOffset
            )
        } catch (exception: IOException) {
            onEvent(UIEvent.ShowError("" + exception))
            LoadResult.Error(exception)
        } catch (http: HttpException) {
            onEvent(UIEvent.ShowError(http.localizedMessage ?: "Unknown HTTP error"))
            LoadResult.Error(http)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDetail>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(PAGE_LIMIT) ?: anchorPage.nextKey?.minus(PAGE_LIMIT)
    }
}