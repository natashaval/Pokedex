package com.natashaval.pokedex.model.location


import com.google.gson.annotations.SerializedName

data class Pokedex(
  @SerializedName("name")
  val name: String? = null,
  @SerializedName("url")
  val url: String? = null
)