package com.natashaval.pokedex.model


import com.google.gson.annotations.SerializedName

data class VersionGroup(
  @SerializedName("name")
  val name: String? = null,
  @SerializedName("url")
  val url: String? = null
)