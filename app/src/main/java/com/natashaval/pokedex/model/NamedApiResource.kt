package com.natashaval.pokedex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NamedApiResource(
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null
) : Parcelable