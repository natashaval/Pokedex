package com.natashaval.pokedex.model.berry

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 19/10/20.
 */
@Parcelize
data class BerryFirmness(
    @SerializedName("id")
    val id: Integer? = null,
    @SerializedName("name")
    val name: String? = null
) : Parcelable