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

  val iconMap = mapOf<String, Int>(
      "normal" to R.drawable.ic_dots_horizontal,
      "fighting" to R.drawable.ic_fighting,
      "flying" to R.drawable.ic_bee,
      "poison" to R.drawable.ic_poison,
      "ground" to R.drawable.ic_shovel,
      "rock" to R.drawable.ic_rock,
      "bug" to R.drawable.ic_ladybug,
      "ghost" to R.drawable.ic_ghost,
      "steel" to R.drawable.ic_settings,
      "fire" to R.drawable.ic_fire,
      "water" to R.drawable.ic_water,
      "grass" to R.drawable.ic_leaf,
      "electric" to R.drawable.ic_flash,
      "psychic" to R.drawable.ic_psychic,
      "ice" to R.drawable.ic_cube_outline,
      "dragon" to R.drawable.ic_dragon,
      "dark" to R.drawable.ic_square_rounded,
      "fairy" to R.drawable.ic_magic_wand,
      "unknown" to R.drawable.ic_help_circle,
      "shadow" to R.drawable.ic_blur,
  )
  }
}