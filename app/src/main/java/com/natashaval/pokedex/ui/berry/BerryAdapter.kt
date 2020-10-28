package com.natashaval.pokedex.ui.berry

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ItemBerryBinding
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.utils.Constant
import com.natashaval.pokedex.utils.DiffUtilCallback
import com.natashaval.pokedex.utils.setSafeClickListener

/**
 * Created by natasha.santoso on 28/10/20.
 */
class BerryAdapter(private val listener:(NamedApiResource?) -> Unit) : PagingDataAdapter<NamedApiResource, BerryAdapter.BerryViewHolder>(DiffUtilCallback()) {
  override fun onBindViewHolder(holder: BerryAdapter.BerryViewHolder, position: Int) {
    holder.bind(getItem(position), listener)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BerryAdapter.BerryViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berry, parent, false)
    return BerryViewHolder(view)
  }

  inner class BerryViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val view = v
    var itemBinding: ItemBerryBinding? = null

    init {
      itemBinding = ItemBerryBinding.bind(view)
    }

    fun bind(namedApiResource: NamedApiResource?, listener: (NamedApiResource?) -> Unit?) {
      itemBinding?.run {
        tvName.text = namedApiResource?.name
        Glide.with(view).load(buildSpriteUrl(namedApiResource?.name)).into(ivSprite)
        root.setSafeClickListener { listener(namedApiResource) }
      }
    }

    private fun buildSpriteUrl(name: String?): String? {
      return Uri.parse(Constant.ITEM_SPRITE_URL).buildUpon()
          .appendPath("$name-berry.png").build().toString()
    }

  }
}