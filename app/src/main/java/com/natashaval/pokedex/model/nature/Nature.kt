package com.natashaval.pokedex.model.nature


import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.NamedApiResource

data class Nature(
  @SerializedName("decreased_stat")
  val decreasedStat: NamedApiResource? = null,
  @SerializedName("hates_flavor")
  val hatesFlavor: NamedApiResource? = null,
  @SerializedName("id")
  val id: Int? = null,
  @SerializedName("increased_stat")
  val increasedStat: NamedApiResource? = null,
  @SerializedName("likes_flavor")
  val likesFlavor: NamedApiResource? = null,
  @SerializedName("move_battle_style_preferences")
  val moveBattleStylePreferences: List<MoveBattleStylePreference>? = null,
  @SerializedName("name")
  val name: String? = null,
  @SerializedName("pokeathlon_stat_changes")
  val pokeathlonStatChanges: List<PokeathlonStatChange>? = null
)