package com.natashaval.pokedex.di

import com.natashaval.pokedex.database.AppDatabase
import com.natashaval.pokedex.database.dao.TypeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by natasha.santoso on 01/11/20.
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
class DaoModule {
  @Provides
  fun provideTypeDao(database: AppDatabase): TypeDao {
    return database.typeDao()
  }
}