package com.natashaval.pokedex.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ItemBerryBinding
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.utils.DiffUtilCallback
import com.natashaval.base.utils.setSafeClickListener
import com.natashaval.pokedex.utils.PokemonUtils.buildSpriteUrl

/**
 * Created by natasha.santoso on 20/10/20.
 */
class ItemAdapter(private val listener: (NamedApiResource?) -> Unit) : PagingDataAdapter<NamedApiResource, ItemAdapter.ItemViewHolder>(DiffUtilCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berry, parent, false)
    return ItemViewHolder(view)
  }

  override fun onBindViewHolder(viewHolder: ItemViewHolder, position: Int) {
    viewHolder.bind(getItem(position), listener)
  }

  inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val view = v
    var itemBinding: ItemBerryBinding? = null

    init {
      itemBinding = ItemBerryBinding.bind(v)
    }

    fun bind(namedApiResource: NamedApiResource?, listener: (NamedApiResource?) -> Unit) {
      itemBinding?.run {
        tvName.text = namedApiResource?.name
        Glide.with(view).load(buildSpriteUrl("${namedApiResource?.name}.png")).into(ivSprite)
        root.setSafeClickListener { listener(namedApiResource) }
      }
    }
  }

}