package com.natashaval.pokedex.model.type


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.NamedApiResource
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DamageRelations(
  @SerializedName("double_damage_from")
  val doubleDamageFrom: List<NamedApiResource>? = null,
  @SerializedName("double_damage_to")
  val doubleDamageTo: List<NamedApiResource>? = null,
  @SerializedName("half_damage_from")
  val halfDamageFrom: List<NamedApiResource>? = null,
  @SerializedName("half_damage_to")
  val halfDamageTo: List<NamedApiResource>? = null,
  @SerializedName("no_damage_from")
  val noDamageFrom: List<NamedApiResource>? = null,
  @SerializedName("no_damage_to")
  val noDamageTo: List<NamedApiResource>? = null
): Parcelable