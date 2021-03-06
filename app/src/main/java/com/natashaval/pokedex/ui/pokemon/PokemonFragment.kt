package com.natashaval.pokedex.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.natashaval.pokedex.databinding.FragmentPokemonBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonFragment : Fragment() {
  private var _binding: FragmentPokemonBinding? = null
  private val binding get() = _binding!!
  private val viewModel: PokemonViewModel by viewModels()
  private val mAdapter = PokemonAdapter()

  private var pokemonJob: Job? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = FragmentPokemonBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fetchPokemonPaging()
    with(binding) {
      rvPokemon.setHasFixedSize(true)
      rvPokemon.layoutManager = GridLayoutManager(activity, 4)
      rvPokemon.adapter = mAdapter
    }
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun fetchPokemonPaging() {
    pokemonJob?.cancel()
    pokemonJob = lifecycleScope.launch {
      viewModel.fetchPokemonPage().collectLatest {
        mAdapter.submitData(it)
      }
    }
  }
}