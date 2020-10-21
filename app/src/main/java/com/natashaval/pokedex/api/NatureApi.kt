package com.natashaval.pokedex.api

import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.nature.Nature
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Created by natasha.santoso on 21/10/20.
 */
interface NatureApi {
  @GET
  suspend fun getNatureList(@Url url: String?): Response<Resource>

  @GET("nature/{id}")
  suspend fun getNature(@Path("id") id: String?): Response<Nature>
}