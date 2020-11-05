package com.natashaval.pokedex.ui.item

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.natashaval.pokedex.databinding.FragmentItemBinding
import com.natashaval.pokedex.interfaces.IActivityView
import com.natashaval.pokedex.utils.hideView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ItemFragment : Fragment() {

  private var _binding: FragmentItemBinding? = null
  private val binding get() = _binding!!
  private val itemViewModel: ItemViewModel by viewModels()
  private lateinit var itemAdapter: ItemAdapter
  private var iActivityView: IActivityView? = null

  private var itemJob: Job? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    iActivityView = context as IActivityView
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentItemBinding.inflate(inflater, container, false)
    itemAdapter = ItemAdapter {
      iActivityView?.openItemBottomSheet(ItemBottomSheet.MODE_ITEM, it)
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fetchItemPaging()
    binding.pbLoading.hideView()
    setRecyclerView()
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun fetchItemPaging() {
    itemJob?.cancel()
    itemJob = lifecycleScope.launch {
      itemViewModel.fetchItemPage().collectLatest {
        itemAdapter.submitData(it)
      }
    }
  }

  private fun setRecyclerView() {
    binding.rvItem.apply {
      setHasFixedSize(true)
      layoutManager = GridLayoutManager(activity, 3)
      adapter = itemAdapter
    }
  }

  companion object {
    fun newInstance() = ItemFragment()
  }
}