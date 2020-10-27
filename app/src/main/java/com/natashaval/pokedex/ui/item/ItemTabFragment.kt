package com.natashaval.pokedex.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentItemTabBinding

/**
 * Created by natasha.santoso on 27/10/20.
 */
class ItemTabFragment : Fragment() {
  private var _binding: FragmentItemTabBinding? = null
  private val binding get() = _binding!!
  private lateinit var itemTabAdapter: ItemTabAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentItemTabBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    itemTabAdapter = ItemTabAdapter(this, 2)
    binding.pager.adapter = itemTabAdapter

    TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
      tab.text = resources.getStringArray(R.array.tab_item)[position]
      val tabIcons = listOf(R.drawable.ic_pill, R.drawable.ic_cherry)
      tab.setIcon(tabIcons[position])
    }.attach()
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}