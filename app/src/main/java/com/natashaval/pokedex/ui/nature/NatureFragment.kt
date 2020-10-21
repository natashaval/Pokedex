package com.natashaval.pokedex.ui.nature

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentNatureBinding
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NatureFragment : Fragment() {
  private var _binding: FragmentNatureBinding? = null
  private val binding get() = _binding!!
  private val viewModel: NatureViewModel by viewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentNatureBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    getNatureList()
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  private fun getNatureList() {
    viewModel.getNatureList()
    viewModel.natureResource.observe(viewLifecycleOwner, {
      val data = it.data
      Timber.d("Logging natureResource next: ${data?.next} previous: ${data?.previous}")
    })

    viewModel.natureList.observe(viewLifecycleOwner, {
      when(it.status) {
        Status.SUCCESS -> {
          binding.pbNature.hideView()
          val data = it.data
          data?.let {natureList ->
            Timber.d("Logging natureList: ${natureList.size}")
            natureList.forEach { nature ->
              Timber.d("Logging nature name: ${nature.name}")
            }
          }
        }
        Status.FAILED, Status.ERROR -> {
          binding.pbNature.hideView()
          Timber.e("Logging natureList error!")
        }
        Status.LOADING -> binding.pbNature.showView()
      }
    })
  }

}