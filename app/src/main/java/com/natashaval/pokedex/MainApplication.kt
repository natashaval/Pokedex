package com.natashaval.pokedex

import android.app.Application
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by natasha.santoso on 19/10/20.
 */
@HiltAndroidApp
class MainApplication : SplitCompatApplication() {
  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}