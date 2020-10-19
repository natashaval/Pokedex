package com.natashaval.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by natasha.santoso on 19/10/20.
 */
@HiltAndroidApp
class MainApplication : Application() {
  override fun onCreate() {
    super.onCreate()
  }
}