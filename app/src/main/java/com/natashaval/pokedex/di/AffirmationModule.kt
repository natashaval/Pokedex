package com.natashaval.pokedex.di

import com.natashaval.pokedex.api.AffirmationApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

/**
 * Created by natasha.santoso on 07/11/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class AffirmationModule {
  @Provides
  fun provideAffirmationApi(@AffirmationUrl retrofit: Retrofit): AffirmationApi {
    return retrofit.create(AffirmationApi::class.java)
  }
}