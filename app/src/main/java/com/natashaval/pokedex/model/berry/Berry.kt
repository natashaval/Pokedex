package com.natashaval.pokedex.model.berry

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.natashaval.pokedex.model.NamedApiResource
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 19/10/20.
 */
@Parcelize
data class Berry(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("growth_time") val growthTime: Int? = null,
    @SerializedName("max_harvest") val maxHarvest: Int? = null,
    @SerializedName("natural_gift_power") val naturalGiftPower: Int? = null,
    @SerializedName("natural_gift_type") val naturalGiftType: NamedApiResource? = null,
    @SerializedName("size") val size: Int? = null,
    @SerializedName("smoothness") val smoothness: Int? = null,
    @SerializedName("soil_dryness") val soilDryness: Int? = null,
    @SerializedName("firmness") val firmness: NamedApiResource? = null
) : Parcelable