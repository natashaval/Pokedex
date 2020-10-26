package com.natashaval.pokedex.utils

import androidx.recyclerview.widget.DiffUtil
import com.natashaval.pokedex.model.NamedApiResource

/**
 * Created by natasha.santoso on 26/10/20.
 */
class DiffUtilCallback: DiffUtil.ItemCallback<NamedApiResource>() {
  override fun areItemsTheSame(oldItem: NamedApiResource, newItem: NamedApiResource): Boolean {
    return oldItem.url == newItem.url
  }

  override fun areContentsTheSame(oldItem: NamedApiResource, newItem: NamedApiResource): Boolean {
    return oldItem.url == newItem.url && oldItem.name == newItem.name
  }
}