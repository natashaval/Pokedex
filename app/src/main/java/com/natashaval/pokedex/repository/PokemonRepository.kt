package com.natashaval.pokedex.repository

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.natashaval.pokedex.api.PokemonApi
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.ui.pokemon.PokemonPagingSource
import com.natashaval.pokedex.ui.pokemon.PokemonViewModel
import com.natashaval.pokedex.utils.Constant
import com.natashaval.pokedex.utils.ResponseUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by natasha.santoso on 26/10/20.
 */
//https://www.raywenderlich.com/12244218-paging-library-for-android-with-kotlin-creating-infinite-lists
class PokemonRepository @Inject constructor(private val pokemonApi: PokemonApi) {
  //  suspend fun getPokemonList(offset: Int?, limit: Int?): MyResponse<Resource> {
//    val response = pokemonApi.getPokemonList(buildUrl(offset, limit))
//    return ResponseUtils.convert(response)
//  }
  suspend fun getPokemonList(url: String?): MyResponse<Resource> {
    val response = pokemonApi.getPokemonList(url)
    return ResponseUtils.convert(response)
  }

  fun getPokemonPaging(): Flow<PagingData<NamedApiResource>> {
    return Pager(
      config = PagingConfig(pageSize = PokemonViewModel.POKEMON_LIMIT),
      pagingSourceFactory = { PokemonPagingSource(this) }
    ).flow
  }

  private fun buildUrl(offset: Int?, limit: Int?): String? {
    return Uri.parse(Constant.BASE_URL).buildUpon()
      .appendPath("pokemon")
      .appendQueryParameter("offset", offset.toString())
      .appendQueryParameter("limit", limit.toString())
      .build().toString()
  }
}