package com.natashaval.pokedex.model.item

import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.NamedApiResource

data class Item(
    @SerializedName("attributes") val attributes: List<Attribute>? = null,
    @SerializedName("category") val category: NamedApiResource? = null,
    @SerializedName("cost") val cost: Int? = null,
    @SerializedName("effect_entries") val effectEntries: List<EffectEntry>? = null,
    @SerializedName("fling_effect") val flingEffect: NamedApiResource? = null,
    @SerializedName("fling_power") val flingPower: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("sprites") val sprites: Sprites? = null
)