package com.natashaval.affirmation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 07/11/20.
 */
@Parcelize
data class Affirmation(
    @SerializedName("affirmation")
    val affirmation: String? = null
): Parcelable