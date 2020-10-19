package com.natashaval.pokedex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 19/10/20.
 */
@Parcelize
data class Resource(
    @SerializedName("count") val count: Integer? = null,
    @SerializedName("next") val next: String? = null,
    @SerializedName("previous") val previous: String? = null,
    @SerializedName("results") val results: List<NamedApiResource>? = null
) : Parcelable