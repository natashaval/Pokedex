package com.natashaval.pokedex.ui.type

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentTypeBinding
import com.natashaval.pokedex.interfaces.IActivityView
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.setSafeClickListener
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
  private val typeViewModel: TypeViewModel by activityViewModels()
  private var iActivityView: IActivityView? = null

  override fun onAttach(context: Context) {
    super.onAttach(context)
    iActivityView = context as IActivityView
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentTypeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    observeType()
    binding.clType.btPrimary.setSafeClickListener {
      typeViewModel.setTypeMode(TypeBottomSheet.MODE_PRIMARY)
      iActivityView?.openTypeBottomSheet()
    }
    binding.clType.btSecondary.setSafeClickListener {
      typeViewModel.setTypeMode(TypeBottomSheet.MODE_SECONDARY)
      iActivityView?.openTypeBottomSheet()
    }
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

  override fun onDetach() {
    iActivityView = null
    super.onDetach()
  }

}