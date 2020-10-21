package com.natashaval.pokedex.model.nature


import com.google.gson.annotations.SerializedName

data class MoveBattleStylePreference(
  @SerializedName("high_hp_preference")
  val highHpPreference: Int? = null,
  @SerializedName("low_hp_preference")
  val lowHpPreference: Int? = null,
  @SerializedName("move_battle_style")
  val moveBattleStyle: MoveBattleStyle? = null
)