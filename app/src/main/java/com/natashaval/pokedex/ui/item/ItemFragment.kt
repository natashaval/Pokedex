package com.natashaval.pokedex.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.natashaval.pokedex.databinding.FragmentItemBinding
import com.natashaval.pokedex.model.MyResponse
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.model.item.Item
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ItemFragment : Fragment() {

  private var _binding: FragmentItemBinding? = null
  private val binding get() = _binding!!
  private val itemViewModel: ItemViewModel by viewModels()
  private lateinit var itemAdapter: ItemAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentItemBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getItemList()
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun getItemList() {
    itemViewModel.getItemList()
    itemViewModel.itemList.observe(viewLifecycleOwner, {
      when(it.status) {
        Status.SUCCESS -> {
          binding.pbItem.hideView()
          val data = it.data
          data?.let { itemList ->
            Timber.d("Logging itemList: ${itemList.size}")
            setReyclerView(itemList)
            itemList.forEach {item ->
              Timber.d("Logging item name: ${item.name}")
            }
          }
        }
        Status.FAILED, Status.ERROR -> {
          binding.pbItem.hideView()
          Timber.e("Logging itemList error!")
        }
        Status.LOADING -> binding.pbItem.showView()
      }
    })
  }

  private fun setReyclerView(items: List<Item>) {
    itemAdapter = ItemAdapter(items)
    binding.rvItem.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(activity)
      adapter = itemAdapter
    }
  }

  companion object {
    fun newInstance() = ItemFragment()
  }
}