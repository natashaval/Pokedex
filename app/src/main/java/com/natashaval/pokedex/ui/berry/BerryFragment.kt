package com.natashaval.pokedex.ui.berry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.natashaval.pokedex.databinding.FragmentItemBinding
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by natasha.santoso on 27/10/20.
 */
@AndroidEntryPoint
class BerryFragment: Fragment() {
  private var _binding: FragmentItemBinding? = null
  private val binding get() = _binding!!
  private val viewModel: BerryViewModel by viewModels()
  private lateinit var mAdapter: BerryAdapter

  private var berryJob: Job? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentItemBinding.inflate(inflater, container, false)
    mAdapter = BerryAdapter {
      Toast.makeText(requireContext(), "Hello ${it?.name}", Toast.LENGTH_SHORT).show()
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fetchBerryPaging()
    binding.pbItem.hideView()
    with (binding.rvItem) {
      setHasFixedSize(true)
      layoutManager = GridLayoutManager(activity, 3)
      adapter = mAdapter
    }
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun fetchBerryPaging() {
    berryJob?.cancel()
    berryJob = lifecycleScope.launch {
      viewModel.fetchBerryPage().collectLatest {
        mAdapter.submitData(it)
      }
    }
  }

  companion object {
    fun newInstance() = BerryFragment()
  }
}