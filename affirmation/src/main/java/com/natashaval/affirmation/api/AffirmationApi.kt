package com.natashaval.affirmation.api

import com.natashaval.affirmation.model.Affirmation
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by natasha.santoso on 07/11/20.
 */
interface AffirmationApi {
  @GET(".")
  suspend fun getAffirmation(): Response<Affirmation>
}