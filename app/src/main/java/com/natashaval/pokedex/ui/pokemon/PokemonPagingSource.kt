package com.natashaval.pokedex.ui.pokemon

import android.net.Uri
import androidx.paging.PagingSource
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.repository.PokemonRepository
import com.natashaval.pokedex.utils.Constant
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by natasha.santoso on 26/10/20.
 */
//https://developer.android.com/codelabs/android-paging#4
class PokemonPagingSource @Inject constructor(private val repository: PokemonRepository) :
  PagingSource<Int, NamedApiResource>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NamedApiResource> {
//    https://stackoverflow.com/questions/63418745/paging-3-initial-loading-not-shown
    return try {
      val currentPage = params.key ?: 0
      val offset = currentPage * PokemonViewModel.POKEMON_LIMIT

      val response = repository.getPokemonList(offset, PokemonViewModel.POKEMON_LIMIT)
      val resource = response.data?.results
      LoadResult.Page(
        data = resource ?: listOf(),
        prevKey = null,
        nextKey = if (response.data?.next == null) null else currentPage + 1
      )
    } catch (exception: IOException) {
      return LoadResult.Error(exception)
    } catch (exception: HttpException) {
      return LoadResult.Error(exception)
    }
  }
}