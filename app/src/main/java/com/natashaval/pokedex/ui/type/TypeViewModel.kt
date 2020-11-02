package com.natashaval.pokedex.ui.type

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.model.type.DamageRelations
import com.natashaval.pokedex.repository.TypeRepository
import com.natashaval.pokedex.ui.type.TypeBottomSheet.Companion.MODE_PRIMARY
import com.natashaval.pokedex.ui.type.TypeBottomSheet.Companion.MODE_SECONDARY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TypeViewModel @ViewModelInject constructor(private val repository: TypeRepository) :
    ViewModel() {

  private var _typeMode: MutableLiveData<Int> = MutableLiveData(-1)
  val typeMode: LiveData<Int> get() = _typeMode

  private var _typePrimary: MutableLiveData<EntityType> = MutableLiveData(EntityType(""))
  val typePrimary: LiveData<EntityType> get() = _typePrimary

  private var _typeSecondary: MutableLiveData<EntityType> = MutableLiveData(EntityType(""))
  val typeSecondary: LiveData<EntityType> get() = _typeSecondary

  private var damagePrimaryMap: MutableMap<String, Double> = mutableMapOf()
  private var damageSecondaryMap: MutableMap<String, Double> = mutableMapOf()
  var damageTotalMap = mutableMapOf("" to 0.0)
  private var _damage: MutableLiveData<Map<String, Double>> = MutableLiveData(mapOf())
  val damage: LiveData<Map<String, Double>> get() = _damage

  fun setTypeMode(mode: Int) {
    _typeMode.value = mode
  }

  fun setTypePrimary(type: EntityType?) {
    _typePrimary.value = type
    CoroutineScope(Dispatchers.IO).launch {
      val response = repository.getType(type?.name)
      if (response.status == Status.SUCCESS) {
        countDamage(response.data?.damageRelations)
      }
    }
  }

  fun setTypeSecondary(type: EntityType?) {
    _typeSecondary.value = type
    CoroutineScope(Dispatchers.IO).launch {
      val response = repository.getType(type?.name)
      if (response.status == Status.SUCCESS) {
        countDamage(response.data?.damageRelations)
      }
    }
  }

  private fun countDamage(damageRelations: DamageRelations?) {
    damageRelations?.doubleDamageFrom?.forEach {
      insertMapValue(it.name.orEmpty(), 2.0)
    }
    damageRelations?.halfDamageFrom?.forEach {
      insertMapValue(it.name.orEmpty(), 0.5)
    }
    damageRelations?.noDamageFrom?.forEach {
      insertMapValue(it.name.orEmpty(), 0.0)
    }
    countDamageMap()
  }

  private fun insertMapValue(name: String, value: Double) {
    when (typeMode.value) {
      MODE_PRIMARY -> damagePrimaryMap[name] = value
      MODE_SECONDARY -> damageSecondaryMap[name] = value
    }
  }

  private fun countDamageMap() {
    for ((key,value) in damageTotalMap) {
      damageTotalMap[key] = (damagePrimaryMap[key] ?: 1.0).times(damageSecondaryMap[key] ?: 1.0)
    }
    _damage.postValue(damageTotalMap)
  }

  val types: LiveData<MyResponse<List<EntityType>>> = repository.saveTypeDatabase(TYPE_OFFSET,
      TYPE_LIMIT)

  val typeList = repository.getTypeDao()

  fun setDamageMap(allTypes: List<EntityType>?) {
    allTypes?.forEach { damageTotalMap[it.name] = 1.0 }
    _damage.postValue(damageTotalMap)
  }

  companion object {
    const val TYPE_OFFSET = 0
    const val TYPE_LIMIT = 20
  }
}