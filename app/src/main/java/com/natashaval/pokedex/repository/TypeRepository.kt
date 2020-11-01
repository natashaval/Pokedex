package com.natashaval.pokedex.repository

import com.natashaval.pokedex.api.TypeApi
import com.natashaval.pokedex.database.dao.TypeDao
import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.type.PokemonType
import com.natashaval.pokedex.utils.DatabaseUtils.performGetOperation
import com.natashaval.pokedex.utils.ResponseUtils
import com.natashaval.pokedex.utils.ResponseUtils.buildUrl
import javax.inject.Inject

/**
 * Created by natasha.santoso on 31/10/20.
 */
//https://itnext.io/android-architecture-hilt-mvvm-kotlin-coroutines-live-data-room-and-retrofit-ft-8b746cab4a06
//https://proandroiddev.com/android-architecture-starring-kotlin-coroutines-jetpack-mvvm-room-paging-retrofit-and-dagger-7749b2bae5f7
class TypeRepository @Inject constructor(private val typeApi: TypeApi, private val typeDao: TypeDao) {
  private suspend fun getTypeList(offset: Int?, limit: Int?): MyResponse<Resource> {
    val response = typeApi.getTypeList(buildUrl("type", offset, limit))
    return ResponseUtils.convert(response)
  }

  suspend fun getType(id: String?): MyResponse<PokemonType> {
    val response = typeApi.getType(id)
    return ResponseUtils.convert(response)
  }

  fun getTypeDao() = typeDao.getAll()

  fun saveTypeDatabase(offset: Int?, limit: Int?) = performGetOperation(
      databaseQuery = { typeDao.getAll() },
      networkCall = { getTypeList(offset, limit) },
      saveCallResult = { typeDao.insertAll(it.results!!.map { res -> EntityType(res.name!!) }) })
}