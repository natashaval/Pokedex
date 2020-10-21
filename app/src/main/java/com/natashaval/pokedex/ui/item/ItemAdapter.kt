package com.natashaval.pokedex.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ItemMainBinding
import com.natashaval.pokedex.model.item.Item

/**
 * Created by natasha.santoso on 20/10/20.
 */
class ItemAdapter(private val itemList: List<Item>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
    return ItemViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
    viewHolder.bind(itemList[position])
  }

  override fun getItemCount(): Int = itemList.size

  inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val view = v
    var itemBinding: ItemMainBinding? = null

    init {
      itemBinding = ItemMainBinding.bind(v)
    }

    fun bind(item: Item?) {
      itemBinding?.run {
        tvTitle.text = item?.name
        tvDescription.text = item?.effectEntries?.get(0)?.shortEffect
        Glide.with(view).load(item?.sprites?.default).into(ivIcon)
      }
    }
  }

}