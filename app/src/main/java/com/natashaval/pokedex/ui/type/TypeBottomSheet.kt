package com.natashaval.pokedex.ui.type

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.database.entity.EntityType
import com.natashaval.pokedex.databinding.BottomSheetTypeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    TypeBottomSheet.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
@AndroidEntryPoint class TypeBottomSheet : BottomSheetDialogFragment() {
  private var _binding: BottomSheetTypeBinding? = null
  private val binding get() = _binding!!

  private val typeViewModel: TypeViewModel by activityViewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = BottomSheetTypeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    typeViewModel.typeMode.observe(viewLifecycleOwner, { mode ->
      when (mode) {
        MODE_PRIMARY -> binding.tvSelect.text = getString(R.string.select_primary_type)
        MODE_SECONDARY -> binding.tvSelect.text = getString(R.string.select_secondary_type)
      }
    })

    observeType()
  }

  private fun observeType() {
    typeViewModel.typeList.observe(viewLifecycleOwner, {
      if (it.isNotEmpty()) {
        val typeList = listOf(EntityType("")) + it
        with(binding.rvType) {
          setHasFixedSize(true)
          layoutManager = GridLayoutManager(activity, 2)
          adapter = TypeAdapter(requireContext(), typeList) { type ->
            dismiss()
            when (typeViewModel.typeMode.value) {
              MODE_PRIMARY -> typeViewModel.setTypePrimary(type)
              MODE_SECONDARY -> typeViewModel.setTypeSecondary(type)
            }
          }
        }
      } else {
        Toast.makeText(activity, "type is empty!", Toast.LENGTH_SHORT).show()
      }
    })
  }

  companion object {
    const val MODE_PRIMARY = 0
    const val MODE_SECONDARY = 1
    const val TYPE_BOTTOM_SHEET_TAG = "typeBottomSheet"

    fun newInstance() = TypeBottomSheet()

  }
}