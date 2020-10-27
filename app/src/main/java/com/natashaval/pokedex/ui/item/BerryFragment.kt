package com.natashaval.pokedex.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.natashaval.pokedex.databinding.FragmentItemBinding
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by natasha.santoso on 27/10/20.
 */
@AndroidEntryPoint
class BerryFragment: Fragment() {
  private var _binding: FragmentItemBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentItemBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.pbItem.showView()
    binding.rvItem.hideView()
  }

  companion object {
    fun newInstance() = BerryFragment()
  }
}