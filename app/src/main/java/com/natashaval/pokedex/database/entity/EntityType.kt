package com.natashaval.pokedex.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by natasha.santoso on 01/11/20.
 */
@Parcelize
@Entity(tableName = "type")
data class EntityType(
    @PrimaryKey val name: String
) : Parcelable