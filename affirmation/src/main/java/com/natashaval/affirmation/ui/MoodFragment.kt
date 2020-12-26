package com.natashaval.affirmation.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.natashaval.affirmation.databinding.FragmentMoodBinding
import com.natashaval.affirmation.viewmodel.MoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoodFragment : Fragment() {

  companion object {
    fun newInstance() = MoodFragment()
  }

  private val viewModel: MoodViewModel by viewModels()
  private var _binding: FragmentMoodBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentMoodBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

}