package com.natashaval.pokedex.model.location

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 19/10/20.
 */
@Parcelize
class Location (
    @SerializedName("id")
    val id: Integer? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("region")
    val region: Region? = null
): Parcelable