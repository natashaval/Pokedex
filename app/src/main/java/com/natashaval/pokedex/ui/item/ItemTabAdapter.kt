package com.natashaval.pokedex.ui.item

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by natasha.santoso on 27/10/20.
 */
class ItemTabAdapter(fragment: Fragment, val count: Int) : FragmentStateAdapter(fragment) {
  override fun getItemCount(): Int = count

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> ItemFragment.newInstance()
      else -> BerryFragment.newInstance()
    }
  }

}