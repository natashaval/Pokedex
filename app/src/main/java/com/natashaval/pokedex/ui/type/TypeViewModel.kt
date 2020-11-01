package com.natashaval.pokedex.ui.type

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.repository.TypeRepository

class TypeViewModel @ViewModelInject constructor(private val repository: TypeRepository): ViewModel() {

  private var _typeMode: MutableLiveData<Int> = MutableLiveData(-1)
  val typeMode: LiveData<Int> get() = _typeMode

  fun setTypeMode(mode: Int) {
    _typeMode.value = mode
  }

  val types: LiveData<MyResponse<List<EntityType>>> = repository.saveTypeDatabase(TYPE_OFFSET, TYPE_LIMIT)

  val typeList = repository.getTypeDao()

  companion object {
    const val TYPE_OFFSET = 0
    const val TYPE_LIMIT = 20
  }
}