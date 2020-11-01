package com.natashaval.pokedex.ui.type

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.repository.TypeRepository
import kotlinx.coroutines.CoroutineScope

class TypeViewModel @ViewModelInject constructor(private val repository: TypeRepository): ViewModel() {

  val types: LiveData<MyResponse<List<EntityType>>> = repository.getTypeDatabase(TYPE_OFFSET, TYPE_LIMIT)

  companion object {
    const val TYPE_OFFSET = 0
    const val TYPE_LIMIT = 20
  }
}