package com.natashaval.pokedex.ui.affirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentAffirmationBinding
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.setSafeClickListener
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AffirmationFragment : Fragment() {

  companion object {
    fun newInstance() = AffirmationFragment()
  }

  private var _binding: FragmentAffirmationBinding? = null
  private val binding get() = _binding!!
  private val viewModel: AffirmationViewModel by viewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentAffirmationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.btAffirmation.setSafeClickListener { viewModel.getAffirmation() }
    viewModel.affirmation.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          binding.tvAffirmation.text = it.data?.affirmation
        }
        Status.LOADING -> binding.pbLoading.showView()
        Status.ERROR, Status.FAILED -> binding.pbLoading.hideView()
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}