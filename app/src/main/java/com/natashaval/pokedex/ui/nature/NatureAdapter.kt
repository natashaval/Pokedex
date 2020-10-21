package com.natashaval.pokedex.ui.nature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ItemNatureBinding
import com.natashaval.pokedex.model.nature.Nature

/**
 * Created by natasha.santoso on 21/10/20.
 */
// https://www.codepolitan.com/cara-membuat-pagination-atau-load-more-menggunakan-recyclerview-part-1-59c689b1b2e76
class NatureAdapter(private val natureList: MutableList<Nature>) :
    RecyclerView.Adapter<NatureAdapter.BaseViewHolder>() {
  private var isLoading: Boolean = false

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    return when (viewType) {
      VIEW_TYPE_ITEM -> {
        NatureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_nature, parent, false)
        )
      }
      else -> {
        LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        )
      }
    }
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    if (holder.itemViewType == VIEW_TYPE_ITEM && holder is NatureViewHolder) {
      holder.bind(natureList[position])
    }
  }

  override fun getItemViewType(position: Int): Int {
    return if (isLoading) {
      if (position == natureList.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    } else {
      VIEW_TYPE_ITEM
    }
  }

  override fun getItemCount(): Int = natureList.size

  fun addItems(newNatureList: List<Nature>) {
    natureList.addAll(newNatureList)
    notifyDataSetChanged()
  }

  fun addLoading() {
    isLoading = true
    natureList.add(Nature())
    notifyItemInserted(natureList.size - 1)
  }

  fun removeLoading() {
    isLoading = false
    val position = natureList.size - 1
    natureList.removeAt(position)
    notifyItemRemoved(position)
  }

  fun clear() {
    natureList.clear()
    notifyDataSetChanged()
  }

  open class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

  inner class NatureViewHolder(itemView: View) : BaseViewHolder(itemView) {
    var binding: ItemNatureBinding? = null

    init {
      binding = ItemNatureBinding.bind(itemView)
    }

    fun bind(nature: Nature?) {
      binding?.run {
        tvName.text = nature?.name
        tvBerryLike.text = if (!nature?.likesFlavor?.name.isNullOrEmpty())
          tvBerryLike.context.getString(R.string.flavor_like, nature?.likesFlavor?.name) else "-"
        tvBerryHate.text = if (!nature?.hatesFlavor?.name.isNullOrEmpty())
          tvBerryHate.context.getString(R.string.flavor_hate, nature?.hatesFlavor?.name) else "-"
        tvIncrease.text = if (!nature?.increasedStat?.name.isNullOrEmpty())
          nature?.increasedStat?.name else "-"
        tvDecrease.text = if (!nature?.decreasedStat?.name.isNullOrEmpty())
          nature?.decreasedStat?.name else "-"
      }
    }
  }

  inner class LoadingViewHolder(itemView: View?) : BaseViewHolder(itemView)

  companion object {
    private const val VIEW_TYPE_LOADING = 0
    private const val VIEW_TYPE_ITEM = 1
  }
}