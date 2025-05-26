package com.example.anotherpokedex.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.anotherpokedex.data.repository.mappers.toDomain
import com.example.anotherpokedex.domain.model.Pokemon

class PokemonListPagingSource(
    private val apiService: PokeApiService
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0
        return try {
            val listResponse = apiService.getPokemonList(limit = params.loadSize, offset = offset)
            val pokemonList = listResponse.results.mapNotNull { resource ->
                try {
                    val detail = apiService.getPokemonDetails(resource.name)
                    detail.toDomain()
                } catch (e: Exception) {
                    Log.e("PagingSource", "Failed to fetch details for ${resource.name}", e)
                    null
                }
            }

            LoadResult.Page(
                data = pokemonList,
                prevKey = if (offset == 0) null else offset - params.loadSize,
                nextKey = if (listResponse.next == null) null else offset + params.loadSize
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}