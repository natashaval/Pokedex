package com.natashaval.affirmation.repository

import com.natashaval.affirmation.api.AffirmationApi
import com.natashaval.base.model.MyResponse
import com.natashaval.affirmation.model.Affirmation
import com.natashaval.base.utils.ResponseUtils
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