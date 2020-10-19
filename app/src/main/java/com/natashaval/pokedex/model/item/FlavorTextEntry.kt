package com.natashaval.pokedex.model.item


import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.VersionGroup

data class FlavorTextEntry(
  @SerializedName("text")
  val text: String? = null,
  @SerializedName("version_group")
  val versionGroup: VersionGroup? = null
)