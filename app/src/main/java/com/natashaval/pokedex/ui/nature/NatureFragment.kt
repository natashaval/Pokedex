package com.natashaval.pokedex.ui.nature

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.natashaval.pokedex.databinding.FragmentNatureBinding
import com.natashaval.base.model.Status
import com.natashaval.pokedex.model.nature.Nature
import com.natashaval.pokedex.ui.adapter.PaginationListener
import com.natashaval.base.utils.hideView
import com.natashaval.base.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint class NatureFragment : Fragment() {
  private var _binding: FragmentNatureBinding? = null
  private val binding get() = _binding!!
  private val viewModel: NatureViewModel by viewModels()

  private lateinit var mAdapter: NatureAdapter
  private var currentPage = NatureViewModel.NATURE_OFFSET
  private var isLastPage = false
  private var totalPage = 0
  private var isLoading = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentNatureBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getNatureList()

    with(binding.rvNature) {
      setHasFixedSize(true)
      val mLayoutManager = LinearLayoutManager(activity)
      mAdapter = NatureAdapter(mutableListOf())
      layoutManager = mLayoutManager
      adapter = mAdapter
      addOnScrollListener(object : PaginationListener(mLayoutManager, NatureViewModel.NATURE_LIMIT) {
        override fun isLoading(): Boolean {
          return isLoading
        }

        override fun isLastPage(): Boolean {
          return isLastPage
        }

        override fun loadMoreItems() {
          isLoading = true
          currentPage += NatureViewModel.NATURE_LIMIT
          viewModel.getNatureList(currentPage, NatureViewModel.NATURE_LIMIT)
        }
      })
    }
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun getNatureList() {
    viewModel.getNatureList(0, NatureViewModel.NATURE_LIMIT)
    viewModel.natureResource.observe(viewLifecycleOwner, {
      val data = it.data
      totalPage = data?.count ?: 0
      Timber.d("Logging natureResource next: ${data?.next} previous: ${data?.previous}")
    })

    viewModel.natureList.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          val data = it.data
          addToAdapter(data)
          data?.let { natureList ->
            Timber.d("Logging natureList: ${natureList.size}")
            natureList.forEach { nature ->
              Timber.d("Logging nature name: ${nature.name}")
            }
          }
        }
        Status.FAILED, Status.ERROR -> {
          binding.pbLoading.hideView()
          Timber.e("Logging natureList error!")
        }
        Status.LOADING -> binding.pbLoading.showView()
      }
    })
  }

  private fun addToAdapter(list: MutableList<Nature>?) {
    Handler().postDelayed({
      if (currentPage != PaginationListener.PAGE_START) mAdapter.removeLoading()
      list?.let { mAdapter.addItems(list) }
      if (currentPage < totalPage) {
        mAdapter.addLoading()
      } else {
        isLastPage = true
      }
      isLoading = false
    }, 1500)
  }

}