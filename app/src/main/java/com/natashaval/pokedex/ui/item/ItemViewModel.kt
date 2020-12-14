package com.natashaval.pokedex.ui.item

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.natashaval.base.model.MyResponse
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.item.Item
import com.natashaval.pokedex.repository.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ItemViewModel @ViewModelInject constructor(private val repository: ItemRepository)
  : ViewModel() {
  private val _item = MutableLiveData<MyResponse<Item>>(MyResponse.loading())
  val item: LiveData<MyResponse<Item>> get() = _item

  fun fetchItemPage(): Flow<PagingData<NamedApiResource>> {
    return repository.getItemPaging().cachedIn(viewModelScope)
  }

  fun getItem(id: String?) {
    CoroutineScope(Dispatchers.IO).launch {
      _item.postValue(repository.getItem(id))
    }
  }

  companion object {
    const val ITEM_LIMIT = 10
  }
}