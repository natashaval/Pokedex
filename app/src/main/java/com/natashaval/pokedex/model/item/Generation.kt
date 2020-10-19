package com.natashaval.pokedex.model.item

import com.google.gson.annotations.SerializedName

data class Generation(
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
)