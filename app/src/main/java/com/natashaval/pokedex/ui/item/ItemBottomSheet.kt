package com.natashaval.pokedex.ui.item

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.BottomSheetItemBinding
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.ui.berry.BerryViewModel
import com.natashaval.pokedex.utils.ResponseUtils.buildSpriteUrl
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by natasha.santoso on 28/10/20.
 */
@AndroidEntryPoint class ItemBottomSheet : BottomSheetDialogFragment() {
  private var _binding: BottomSheetItemBinding? = null
  private val binding get() = _binding!!

  private val berryViewModel: BerryViewModel by viewModels()
  private val itemViewModel: ItemViewModel by viewModels()

  private val colorMap = mapOf<String, Int>(
      "normal" to R.color.color_normal,
      "fighting" to R.color.color_fighting,
      "flying" to R.color.color_flying,
      "poison" to R.color.color_poison,
      "ground" to R.color.color_ground,
      "rock" to R.color.color_rock,
      "bug" to R.color.color_bug,
      "ghost" to R.color.color_ghost,
      "steel" to R.color.color_steel,
      "fire" to R.color.color_fire,
      "water" to R.color.color_water,
      "grass" to R.color.color_grass,
      "electric" to R.color.color_electric,
      "psychic" to R.color.color_psychic,
      "ice" to R.color.color_ice,
      "dragon" to R.color.color_dragon,
      "dark" to R.color.color_dark,
      "fairy" to R.color.color_fairy,
      "unknown" to R.color.color_unknown,
      "shadow" to R.color.color_shadow,
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = BottomSheetItemBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val res = arguments?.getParcelable<NamedApiResource>(ITEM_RES)
    val uri = Uri.parse(res?.url)
    binding.tvName.text = res?.name
    binding.tvGroup.text = uri.pathSegments[2]
    when (arguments?.getInt(ITEM_MODE)) {
      MODE_BERRY -> observeBerry(uri.pathSegments[3])
      else -> observeItem(uri.pathSegments[3])
    }
  }

  private fun observeBerry(id: String?) {
    berryViewModel.getBerry(id)
    berryViewModel.berry.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          val berry = it.data
          with(binding) {
            Glide.with(this@ItemBottomSheet).load(buildSpriteUrl("${berry?.name}-berry.png")).into(
                ivSprite)
            category.column1.tvTitle.text = getString(R.string.berry_firmness)
            category.column1.tvDetail.text = berry?.firmness?.name
            category.column2.tvTitle.text = getString(R.string.type)
            category.column2.tvDetail.text = berry?.naturalGiftType?.name
            category.column3.root.hideView()
            category.column4.tvTitle.text = getString(R.string.size)
            category.column4.tvDetail.text = berry?.size.toString()
            category.column5.tvTitle.text = getString(R.string.power)
            category.column5.tvDetail.text = berry?.naturalGiftPower.toString()
            category.column6.tvTitle.text = getString(R.string.growth_time)
            category.column6.tvDetail.text = berry?.growthTime.toString()

            val color = ContextCompat.getColor(requireContext(),
                colorMap[berry?.naturalGiftType?.name] ?: R.color.color_normal)
            clHeader.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
          }
        }
        Status.LOADING -> binding.pbLoading.showView()
        Status.ERROR, Status.FAILED -> {
          binding.pbLoading.hideView()
          dismiss()
        }
      }
    })
  }

  private fun observeItem(id: String?) {
    itemViewModel.getItem(id)
    itemViewModel.item.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          val item = it.data
          with(binding) {
            Glide.with(this@ItemBottomSheet).load(item?.sprites?.default).into(
                ivSprite)
            category.column1.tvTitle.text = getString(R.string.category)
            category.column1.tvDetail.text = item?.category?.name
            category.column2.tvTitle.text = getString(R.string.attributes)
            category.column2.tvDetail.text = item?.attributes?.get(0)?.name
            category.column3.root.hideView()
            category.column4.tvTitle.text = getString(R.string.cost)
            category.column4.tvDetail.text = checkNameNull(item?.cost)
            category.column5.tvTitle.text = getString(R.string.fling_power)
            category.column5.tvDetail.text = checkNameNull(item?.flingPower)
            category.column6.tvTitle.text = getString(R.string.fling_effect)
            category.column6.tvDetail.text = if (null == item?.flingEffect) "-" else item.flingEffect.name

            row1.root.showView()
            row1.tvTitle.text = getString(R.string.effect)
            row1.tvDetail.text = item?.effectEntries?.get(0)?.shortEffect

            row2.root.showView()
            row2.tvTitle.text = getString(R.string.in_depth_effect)
            row2.tvDetail.text = item?.effectEntries?.get(0)?.effect
          }
        }
        Status.LOADING -> binding.pbLoading.showView()
        Status.ERROR, Status.FAILED -> {
          binding.pbLoading.hideView()
          dismiss()
        }
      }
    })
  }

  private fun checkNameNull(value: Int?): String? {
    return value?.toString() ?: "-"
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }

  companion object {
    private const val ITEM_RES = "item_res"
    private const val ITEM_MODE = "item_mode"
    const val MODE_ITEM = 0
    const val MODE_BERRY = 1
    const val ITEM_BOTTOM_SHEET_TAG = "itemBottomSheet"
    fun newInstance(itemMode: Int, namedApiResource: NamedApiResource?) = ItemBottomSheet().apply {
      arguments = Bundle().apply {
        putInt(ITEM_MODE, itemMode)
        putParcelable(ITEM_RES, namedApiResource)
      }
    }
  }
}