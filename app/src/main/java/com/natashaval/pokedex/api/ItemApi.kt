package com.natashaval.pokedex.api

import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.item.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by natasha.santoso on 19/10/20.
 */
interface ItemApi {
  @GET suspend fun getItemList(@Url url: String?): Response<Resource>

  @GET("item/{id}") suspend fun getItem(@Path("id") id: String?): Response<Item>

  @GET suspend fun getBerryList(@Url url: String?): Response<Resource>

  @GET("berry/{id}") suspend fun getBerry(@Path("id") id: String?): Response<Item>
}