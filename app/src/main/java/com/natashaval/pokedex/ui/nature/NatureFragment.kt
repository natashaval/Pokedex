package com.natashaval.pokedex.ui.nature

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.natashaval.pokedex.R

class NatureFragment : Fragment() {

  companion object {
    fun newInstance() = NatureFragment()
  }

  private lateinit var viewModel: NatureViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.nature_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(NatureViewModel::class.java)
    // TODO: Use the ViewModel
  }

}