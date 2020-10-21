package com.natashaval.pokedex.model.nature


import com.google.gson.annotations.SerializedName

data class PokeathlonStatChange(
  @SerializedName("max_change")
  val maxChange: Int? = null,
  @SerializedName("pokeathlon_stat")
  val pokeathlonStat: PokeathlonStat? = null
)