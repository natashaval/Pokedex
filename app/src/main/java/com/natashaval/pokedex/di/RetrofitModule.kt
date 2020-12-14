package com.natashaval.pokedex.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.natashaval.pokedex.utils.Constant.Companion.BASE_POKEAPI_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by natasha.santoso on 18/10/20.
 */
@Module
@InstallIn(ApplicationComponent::class)
class RetrofitModule {
  @Provides
  @Singleton
  @PokeapiUrl
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_POKEAPI_URL).client(okHttpClient).addConverterFactory(
        GsonConverterFactory.create(gson)
    ).build()
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    ).build()
  }

  @Provides
  @Singleton
  fun provideGson(): Gson {
    return GsonBuilder().create()
  }
}