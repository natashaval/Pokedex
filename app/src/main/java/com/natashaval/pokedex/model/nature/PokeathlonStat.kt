package com.natashaval.pokedex.model.nature


import com.google.gson.annotations.SerializedName

data class PokeathlonStat(
  @SerializedName("name")
  val name: String? = null,
  @SerializedName("url")
  val url: String? = null
)