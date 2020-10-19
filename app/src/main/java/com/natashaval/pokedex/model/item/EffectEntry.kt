package com.natashaval.pokedex.model.item

import com.google.gson.annotations.SerializedName

data class EffectEntry(
    @SerializedName("effect") val effect: String? = null,
    @SerializedName("short_effect") val shortEffect: String? = null
)