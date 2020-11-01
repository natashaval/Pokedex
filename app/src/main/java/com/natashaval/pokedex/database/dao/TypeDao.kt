package com.natashaval.pokedex.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.natashaval.pokedex.database.entity.EntityType

/**
 * Created by natasha.santoso on 01/11/20.
 */
@Dao
interface TypeDao {
  @Query("SELECT * FROM type")
  fun getAll(): LiveData<List<EntityType>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(types : List<EntityType>)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(vararg type: EntityType)

  @Delete
  fun delete(type: EntityType)
}