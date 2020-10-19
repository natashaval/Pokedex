package com.natashaval.pokedex.di

import com.natashaval.pokedex.api.ItemApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

/**
 * Created by natasha.santoso on 19/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class ApiModule {
    @Provides
    fun provideItemApi(retrofit: Retrofit): ItemApi {
        return retrofit.create(ItemApi::class.java)
    }
}