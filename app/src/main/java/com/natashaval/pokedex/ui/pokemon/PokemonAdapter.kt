package com.natashaval.pokedex.ui.pokemon

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ItemPokemonBinding
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.utils.DiffUtilCallback
import timber.log.Timber

/**
 * Created by natasha.santoso on 26/10/20.
 */
class PokemonAdapter: PagingDataAdapter<NamedApiResource, PokemonAdapter.PokemonViewHolder>(DiffUtilCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
    return PokemonViewHolder(view)
  }

  override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class PokemonViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private val view = v
    var itemBinding: ItemPokemonBinding? = null

    init {
      itemBinding = ItemPokemonBinding.bind(view)
    }

    fun bind(namedApiResource: NamedApiResource?) {
      itemBinding?.run {
        tvName.text = namedApiResource?.name
        Glide.with(view).load(buildArtworkUrl(namedApiResource?.url)).into(ivSprite)
      }
    }

    private fun buildArtworkUrl(url: String?): String? {
      val id = Uri.parse(url).pathSegments[3]
      val uri = Uri.parse(POKEMON_ARTWORK).buildUpon()
        .appendPath("$id.png").build().toString()
      Timber.d("Logging artwork url: $uri")
      return uri
    }
  }

  companion object {
    private const val POKEMON_ARTWORK =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
  }
}