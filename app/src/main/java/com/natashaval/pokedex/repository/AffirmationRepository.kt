package com.natashaval.pokedex.repository

import com.natashaval.pokedex.api.AffirmationApi
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.affirmation.Affirmation
import com.natashaval.pokedex.utils.ResponseUtils
import javax.inject.Inject

/**
 * Created by natasha.santoso on 07/11/20.
 */
class AffirmationRepository @Inject constructor(private val affirmationApi: AffirmationApi) {
  suspend fun getAffirmation(): MyResponse<Affirmation> {
    val response = affirmationApi.getAffirmation()
    return ResponseUtils.convert(response)
  }
}