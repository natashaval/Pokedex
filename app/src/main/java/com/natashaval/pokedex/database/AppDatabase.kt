package com.natashaval.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.natashaval.pokedex.database.dao.TypeDao
import com.natashaval.pokedex.database.entity.EntityType

/**
 * Created by natasha.santoso on 01/11/20.
 */
@Database(entities = [EntityType::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun typeDao(): TypeDao

  companion object {
    @Volatile private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase = instance ?: synchronized(this) {
      instance ?: buildDatabase(context).also { instance = it }
    }

    private fun buildDatabase(appContext: Context) = Room.databaseBuilder(appContext,
        AppDatabase::class.java, "pokedex-db").fallbackToDestructiveMigration().build()
  }
}