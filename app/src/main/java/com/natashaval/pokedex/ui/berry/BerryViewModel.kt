package com.natashaval.pokedex.ui.berry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.natashaval.base.model.MyResponse
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.berry.Berry
import com.natashaval.pokedex.repository.BerryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by natasha.santoso on 28/10/20.
 */
class BerryViewModel @ViewModelInject constructor(private val repository: BerryRepository) :
    ViewModel() {
  private val _berry = MutableLiveData<MyResponse<Berry>>(MyResponse.loading())
  val berry: LiveData<MyResponse<Berry>> get() = _berry

  fun fetchBerryPage(): Flow<PagingData<NamedApiResource>> {
    return repository.getBerryPaging().cachedIn(viewModelScope)
  }

  fun getBerry(id: String?) {
    CoroutineScope(Dispatchers.IO).launch {
      _berry.postValue(repository.getBerry(id))
    }
  }

  companion object {
    const val BERRY_LIMIT = 20
  }
}