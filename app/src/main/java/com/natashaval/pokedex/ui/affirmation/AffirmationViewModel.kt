package com.natashaval.pokedex.ui.affirmation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.affirmation.Affirmation
import com.natashaval.pokedex.repository.AffirmationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AffirmationViewModel @ViewModelInject constructor(
    private val repository: AffirmationRepository) : ViewModel() {
  private var _affirmation = MutableLiveData<MyResponse<Affirmation>>()
  val affirmation: LiveData<MyResponse<Affirmation>> get() = _affirmation

  fun getAffirmation() {
    _affirmation.value = MyResponse.loading()
    CoroutineScope(Dispatchers.IO).launch {
      _affirmation.postValue(repository.getAffirmation())
    }
  }
}