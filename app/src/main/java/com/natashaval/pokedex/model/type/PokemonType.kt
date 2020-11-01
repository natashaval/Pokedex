package com.natashaval.pokedex.model.type


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.NamedApiResource
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonType(
  @SerializedName("damage_relations")
  val damageRelations: DamageRelations? = null,
  @SerializedName("generation")
  val generation: NamedApiResource? = null,
  @SerializedName("id")
  val id: Int? = null,
  @SerializedName("move_damage_class")
  val moveDamageClass: NamedApiResource? = null,
  @SerializedName("name")
  val name: String? = null
): Parcelable