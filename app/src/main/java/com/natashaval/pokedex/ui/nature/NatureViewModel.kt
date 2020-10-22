package com.natashaval.pokedex.ui.nature

import android.net.Uri
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.Resource
import com.natashaval.pokedex.model.nature.Nature
import com.natashaval.pokedex.repository.NatureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NatureViewModel @ViewModelInject constructor(private val repository: NatureRepository) :
    ViewModel() {
  private val _natureResource = MutableLiveData<MyResponse<Resource>>()
  val natureResource: LiveData<MyResponse<Resource>> get() = _natureResource

  private val _natureList = MutableLiveData<MyResponse<MutableList<Nature>>>(MyResponse.loading())
  val natureList: LiveData<MyResponse<MutableList<Nature>>> get() = _natureList

  fun getNatureList(offset: Int?, limit: Int? = 10) {
    CoroutineScope(Dispatchers.IO).launch {
      val response = repository.getNatureList(buildUrl(offset, limit))
      _natureResource.postValue(response)

      val list = mutableListOf<Nature>()
      response.data?.results?.forEach {
        val id = Uri.parse(it.url).pathSegments[3]
        val myResponse = repository.getNature(id)
        myResponse.data?.let {nature ->
          list.add(nature)
        }
      }
      _natureList.postValue(MyResponse.success(list))
    }
  }

  private fun buildUrl(offset: Int?, limit: Int?): String? {
    return Uri.Builder().appendPath("nature")
        .appendQueryParameter("offset", offset.toString())
        .appendQueryParameter("limit", limit.toString())
        .build().toString()
  }
}
