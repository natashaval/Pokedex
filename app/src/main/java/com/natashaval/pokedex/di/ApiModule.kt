package com.natashaval.pokedex.di

import com.natashaval.pokedex.api.ItemApi
import com.natashaval.pokedex.api.NatureApi
import com.natashaval.pokedex.api.PokemonApi
import com.natashaval.pokedex.api.TypeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit
import javax.inject.Singleton

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
  @Provides
  fun provideNatureApi(retrofit: Retrofit): NatureApi {
    return retrofit.create(NatureApi::class.java)
  }
  @Provides
  fun providePokemonApi(retrofit: Retrofit): PokemonApi {
    return retrofit.create(PokemonApi::class.java)
  }
  @Provides
  fun provideTypeApi(retrofit: Retrofit): TypeApi {
    return retrofit.create(TypeApi::class.java)
  }
}