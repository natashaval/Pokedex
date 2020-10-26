package com.natashaval.pokedex.api

import com.natashaval.pokedex.model.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by natasha.santoso on 26/10/20.
 */
interface PokemonApi {
    @GET
    suspend fun getPokemonList(@Url url: String?): Response<Resource>
}