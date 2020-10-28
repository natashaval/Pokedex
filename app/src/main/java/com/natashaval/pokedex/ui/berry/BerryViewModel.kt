package com.natashaval.pokedex.ui.berry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.repository.BerryRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by natasha.santoso on 28/10/20.
 */
class BerryViewModel @ViewModelInject constructor(private val repository: BerryRepository) :
    ViewModel() {

  fun fetchBerryPage(): Flow<PagingData<NamedApiResource>> {
    return repository.getBerryPaging().cachedIn(viewModelScope)
  }

  companion object {
    const val BERRY_LIMIT = 10
  }
}