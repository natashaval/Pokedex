package com.natashaval.pokedex.ui.type

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentTypeBinding
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TypeFragment : Fragment() {

  companion object {
    fun newInstance() = TypeFragment()
  }

  private var _binding: FragmentTypeBinding? = null
  private val binding get() = _binding!!
  private val typeViewModel: TypeViewModel by viewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentTypeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observeType()
  }

  private fun observeType() {
    typeViewModel.types.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.LOADING -> binding.pbLoading.showView()
        Status.FAILED, Status.ERROR -> binding.pbLoading.hideView()
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          val data = it.data
          data?.forEach { Timber.d("Logging type: ${it.name}") }
        }
      }
    })
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

}