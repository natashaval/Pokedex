package com.natashaval.pokedex.ui.item

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.item.Item
import com.natashaval.pokedex.repository.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel @ViewModelInject constructor(private val repository: ItemRepository)
  : ViewModel() {

  private val _itemList = MutableLiveData<MyResponse<List<Item>>>(MyResponse.loading(null))
  val itemList: LiveData<MyResponse<List<Item>>> get() = _itemList

  fun getItemList() {
    CoroutineScope(Dispatchers.IO).launch {
      val response = repository.getItemList(ITEM_BASE_URL)
      _itemList.postValue(MyResponse.success(response))
    }
  }

  companion object {
    private const val ITEM_BASE_URL = "item?offset=0&limit=8"
  }
}