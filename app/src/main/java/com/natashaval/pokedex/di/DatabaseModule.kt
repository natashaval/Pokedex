package com.natashaval.pokedex.di

import android.content.Context
import com.natashaval.pokedex.database.AppDatabase
import com.natashaval.pokedex.database.dao.TypeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by natasha.santoso on 01/11/20.
 */
//https://codelabs.developers.google.com/codelabs/android-hilt/#6
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
    return AppDatabase.getDatabase(appContext)
  }
}