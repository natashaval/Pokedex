package com.natashaval.pokedex.repository

import com.natashaval.pokedex.api.NatureApi
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.nature.Nature
import com.natashaval.pokedex.utils.ResponseUtils
import javax.inject.Inject

/**
 * Created by natasha.santoso on 21/10/20.
 */
class NatureRepository @Inject constructor(private val natureApi: NatureApi) {
  suspend fun getNatureList(url: String?): MyResponse<Resource> {
    val response = natureApi.getNatureList(url)
    return ResponseUtils.convert(response)
  }

  suspend fun getNature(id: String?): MyResponse<Nature?> {
    val response = natureApi.getNature(id)
    return ResponseUtils.convert(response)
  }
}