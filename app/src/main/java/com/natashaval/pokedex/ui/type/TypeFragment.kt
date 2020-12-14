package com.natashaval.pokedex.ui.type

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.natashaval.base.utils.hideView
import com.natashaval.base.utils.orFalse
import com.natashaval.base.utils.setSafeClickListener
import com.natashaval.base.utils.showView
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentTypeBinding
import com.natashaval.pokedex.interfaces.IActivityView
import com.natashaval.base.model.Status
import com.natashaval.pokedex.utils.*
import dagger.hilt.android.AndroidEntryPoint

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
    observeDamage()
    observeButtonType()
  }

  private fun observeType() {
    typeViewModel.types.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.LOADING -> binding.pbLoading.showView()
        Status.FAILED, Status.ERROR -> binding.pbLoading.hideView()
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          typeViewModel.setDamageMap(it.data)
        }
      }
    })
  }

  private fun observeDamage() {
    typeViewModel.damage.observe(viewLifecycleOwner, { map ->
      binding.clDamage.clWeak.cgDamage.removeAllViews()
      binding.clDamage.clResistant.cgDamage.removeAllViews()
      binding.clDamage.clNormal.cgDamage.removeAllViews()
      val weak = map.filterValues { it > 1.0 }
      binding.clDamage.clWeak.tvDamage.text = getString(R.string.weak_against)
      for((key,value) in weak) {
        binding.clDamage.clWeak.cgDamage.addChip(key, value)
      }
      val resistant = map.filterValues { it < 1.0 }
      binding.clDamage.clResistant.tvDamage.text = getString(R.string.resistant_against)
      for((key,value) in resistant) {
        binding.clDamage.clResistant.cgDamage.addChip(key, value)
      }
      val normal = map.filterValues { it == 1.0 }
      binding.clDamage.clNormal.tvDamage.text = getString(R.string.normal_damage_from)
      for((key,value) in normal) {
        if (key.isNotEmpty()) {
          binding.clDamage.clNormal.cgDamage.addChip(key, value)
        }
      }
    })
  }

  private fun observeButtonType() {
    typeViewModel.typePrimary.observe(viewLifecycleOwner, {
      binding.clType.btPrimary.apply {
        text = if (it.name.isNotEmpty()) it.name else "all types"
        setBackgroundColor(ContextCompat.getColor(requireContext(),
          Constant.colorMap[it.name] ?: R.color.colorPrimary))
        setTextColor(ContextCompat.getColor(requireContext(),
          if (it.name.isNotEmpty()) R.color.greyDark else R.color.white))
        setSafeClickListener {
          typeViewModel.setTypeMode(TypeBottomSheet.MODE_PRIMARY)
          iActivityView?.openTypeBottomSheet()
        }
      }
      if (it.name.isEmpty() && typeViewModel.typeSecondary.value?.name?.isEmpty().orFalse()) {
        binding.clDamage.svDamage.hideView()
        binding.clDamage.tvDamageHint.showView()
      } else {
        binding.clDamage.svDamage.showView()
        binding.clDamage.tvDamageHint.hideView()
      }
    })

    typeViewModel.typeSecondary.observe(viewLifecycleOwner, {
      binding.clType.btSecondary.apply {
        text = if (it.name.isNotEmpty()) it.name else "all types"
        setBackgroundColor(ContextCompat.getColor(requireContext(),
          Constant.colorMap[it.name] ?: R.color.colorPrimary))
        setTextColor(ContextCompat.getColor(requireContext(),
          if (it.name.isNotEmpty()) R.color.greyDark else R.color.white))
        setSafeClickListener {
          typeViewModel.setTypeMode(TypeBottomSheet.MODE_SECONDARY)
          iActivityView?.openTypeBottomSheet()
        }
      }
      if (it.name.isEmpty() && typeViewModel.typePrimary.value?.name?.isEmpty().orFalse()) {
        binding.clDamage.svDamage.hideView()
        binding.clDamage.tvDamageHint.showView()
      } else {
        binding.clDamage.svDamage.showView()
        binding.clDamage.tvDamageHint.hideView()
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

  private fun ChipGroup.addChip(name: String, value: Double) {
    Chip(requireContext()).apply {
      text = "$name  $value"
      chipIcon = ContextCompat.getDrawable(
        requireContext(),
        Constant.iconMap[name] ?: R.drawable.ic_pokeball
      )
      addView(this)
    }
  }
}