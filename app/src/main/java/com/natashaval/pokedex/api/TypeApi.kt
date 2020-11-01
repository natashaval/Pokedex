package com.natashaval.pokedex.api

import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.type.PokemonType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Created by natasha.santoso on 31/10/20.
 */
interface TypeApi {
  @GET suspend fun getTypeList(@Url url: String?): Response<Resource>

  @GET("type/{id}") suspend fun getType(@Path("id") id: String?): Response<PokemonType>
}