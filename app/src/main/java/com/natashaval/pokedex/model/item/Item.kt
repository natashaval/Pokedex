package com.natashaval.pokedex.model.item


import com.google.gson.annotations.SerializedName

data class Item(
  @SerializedName("attributes")
  val attributes: List<Attribute>? = null,
  @SerializedName("baby_trigger_for")
  val babyTriggerFor: Any? = null,
  @SerializedName("category")
  val category: Category? = null,
  @SerializedName("cost")
  val cost: Int? = null,
  @SerializedName("effect_entries")
  val effectEntries: List<EffectEntry>? = null,
  @SerializedName("flavor_text_entries")
  val flavorTextEntries: List<FlavorTextEntry>? = null,
  @SerializedName("fling_effect")
  val flingEffect: Any? = null,
  @SerializedName("fling_power")
  val flingPower: Any? = null,
  @SerializedName("game_indices")
  val gameIndices: List<GameIndice>? = null,
  @SerializedName("held_by_pokemon")
  val heldByPokemon: List<Any>? = null,
  @SerializedName("id")
  val id: Int? = null,
  @SerializedName("machines")
  val machines: List<Any>? = null,
  @SerializedName("name")
  val name: String? = null,
  @SerializedName("sprites")
  val sprites: Sprites? = null
)