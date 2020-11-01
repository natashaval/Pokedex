package com.natashaval.pokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by natasha.santoso on 01/11/20.
 */
@Entity(tableName = "type") data class EntityType(
    @PrimaryKey val name: String
)