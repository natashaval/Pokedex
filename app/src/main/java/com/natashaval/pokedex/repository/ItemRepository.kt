package com.natashaval.pokedex.repository

import android.net.Uri
import com.natashaval.pokedex.api.ItemApi
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.item.Item
import com.natashaval.pokedex.utils.ResponseUtils
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by natasha.santoso on 19/10/20.
 */
class ItemRepository @Inject constructor(private val itemApi: ItemApi) {
  suspend fun getItemList(url: String?): List<Item> {
    val response = itemApi.getItemList(url)
    return getItem(response.body())
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