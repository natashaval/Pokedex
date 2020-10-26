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
  PagingSource<String, NamedApiResource>() {
  override suspend fun load(params: LoadParams<String>): LoadResult<String, NamedApiResource> {
    return try {
      val response = repository.getPokemonList(buildUrl(PokemonViewModel.POKEMON_OFFSET, PokemonViewModel.POKEMON_LIMIT))
      val resource = response.data?.results
      LoadResult.Page(
        data = resource ?: listOf(),
        prevKey = response.data?.previous,
        nextKey = response.data?.next
      )
    } catch (exception: IOException) {
      return LoadResult.Error(exception)
    } catch (exception: HttpException) {
      return LoadResult.Error(exception)
    }
  }

  override val keyReuseSupported: Boolean = true


  private fun buildUrl(offset: Int?, limit: Int?): String? {
    return Uri.parse(Constant.BASE_URL).buildUpon()
      .appendPath("pokemon")
      .appendQueryParameter("offset", offset.toString())
      .appendQueryParameter("limit", limit.toString())
      .build().toString()
  }
}