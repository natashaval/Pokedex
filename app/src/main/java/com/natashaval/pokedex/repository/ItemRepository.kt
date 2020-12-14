package com.natashaval.pokedex.repository

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.natashaval.pokedex.api.ItemApi
import com.natashaval.base.model.MyResponse
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.item.Item
import com.natashaval.pokedex.ui.item.ItemPagingSource
import com.natashaval.pokedex.ui.item.ItemViewModel
import com.natashaval.base.utils.ResponseUtils
import com.natashaval.pokedex.utils.PokemonUtils.buildUrl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by natasha.santoso on 19/10/20.
 */
class ItemRepository @Inject constructor(private val itemApi: ItemApi) {
  suspend fun getItemList(offset: Int?, limit: Int?): MyResponse<Resource> {
    val response = itemApi.getItemList(buildUrl("item", offset, limit))
    return ResponseUtils.convert(response)
  }

  fun getItemPaging(): Flow<PagingData<NamedApiResource>> {
    return Pager(config = PagingConfig(pageSize = ItemViewModel.ITEM_LIMIT),
    pagingSourceFactory = { ItemPagingSource(this)}).flow
  }

  suspend fun getItem(id: String?): MyResponse<Item> {
    val response = itemApi.getItem(id)
    return ResponseUtils.convert(response)
  }

  private suspend fun getItem(resource: Resource?): MutableList<Item> {
    val itemList = mutableListOf<Item>()
    resource?.results?.forEach {
      val id = Uri.parse(it.url).pathSegments[3]
      val response = itemApi.getItem(id)
      val myResponse = ResponseUtils.convert(response)
      myResponse.data?.let {item ->
        itemList.add(item)
      }
    }
    return itemList
  }
}