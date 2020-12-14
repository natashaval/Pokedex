package com.natashaval.pokedex.ui.pokemon

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonViewModel @ViewModelInject constructor(private val repository: PokemonRepository) :
  ViewModel() {

  fun fetchPokemonPage(): Flow<PagingData<NamedApiResource>> {
    return repository.getPokemonPaging().cachedIn(viewModelScope)
  }

  companion object {
    const val POKEMON_OFFSET = 0
    const val POKEMON_LIMIT = 10
  }
}