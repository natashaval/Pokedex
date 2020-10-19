package com.natashaval.pokedex.model.berry

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 19/10/20.
 */
@Parcelize
data class Berry(
    @SerializedName("id") val id: Integer? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("growth_time") val growthTime: Integer? = null,
    @SerializedName("max_harvest") val maxHarvest: Integer? = null,
    @SerializedName("natural_gift_power") val naturalGiftPower: Integer? = null,
    @SerializedName("size") val size: Integer? = null,
    @SerializedName("smoothness") val smoothness: Integer? = null,
    @SerializedName("soil_dryness") val soilDryness: Integer? = null,
    @SerializedName("firmness") val firmness: BerryFirmness? = null,
    @SerializedName("flavors") val flavors: List<BerryFlavor>? = null
) : Parcelable