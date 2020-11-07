package com.natashaval.pokedex.api

import com.natashaval.pokedex.model.affirmation.Affirmation
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by natasha.santoso on 07/11/20.
 */
interface AffirmationApi {
  @GET(".")
  suspend fun getAffirmation(): Response<Affirmation>
}