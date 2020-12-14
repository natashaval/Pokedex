package com.natashaval.pokedex.utils

import android.net.Uri

/**
 * Created by natasha.santoso on 14/12/20.
 */
object PokemonUtils {
  fun buildUrl(path: String, offset: Int?, limit: Int?): String? {
    return Uri.parse(Constant.BASE_POKEAPI_URL).buildUpon()
        .appendPath(path)
        .appendQueryParameter("offset", offset.toString())
        .appendQueryParameter("limit", limit.toString())
        .build().toString()
  }

  fun buildSpriteUrl(name: String?): String? {
    return Uri.parse(Constant.ITEM_SPRITE_URL).buildUpon()
        .appendPath(name).build().toString()
  }
}