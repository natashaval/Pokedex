package com.natashaval.pokedex.utils

import com.natashaval.pokedex.R

/**
 * Created by natasha.santoso on 22/10/20.
 */
class Constant {
  companion object {
    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val POKEMON_ARTWORK_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    const val ITEM_SPRITE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"

    val colorMap = mapOf<String, Int>(
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
  }
}