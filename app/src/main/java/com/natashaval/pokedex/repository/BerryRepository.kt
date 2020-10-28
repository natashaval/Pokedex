package com.natashaval.pokedex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.natashaval.pokedex.api.ItemApi
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.berry.Berry
import com.natashaval.pokedex.ui.berry.BerryPagingSource
import com.natashaval.pokedex.ui.berry.BerryViewModel
import com.natashaval.pokedex.utils.ResponseUtils
import com.natashaval.pokedex.utils.ResponseUtils.buildUrl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by natasha.santoso on 28/10/20.
 */
class BerryRepository @Inject constructor(private val itemApi: ItemApi) {
  suspend fun getBerryList(offset: Int?, limit: Int?): MyResponse<Resource> {
    val response = itemApi.getBerryList(buildUrl("berry", offset, limit))
    return ResponseUtils.convert(response)
  }

  fun getBerryPaging(): Flow<PagingData<NamedApiResource>> {
    return Pager(config = PagingConfig(pageSize = BerryViewModel.BERRY_LIMIT),
    pagingSourceFactory = { BerryPagingSource(this)}).flow
  }

  suspend fun getBerry(id: String?): MyResponse<Berry> {
    val response = itemApi.getBerry(id)
    return ResponseUtils.convert(response)
  }
}