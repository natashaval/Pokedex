package com.natashaval.base.router

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import timber.log.Timber

/**
 * Created by natasha.santoso on 14/12/20.
 */
object RouterUtils {
  fun route(context: Context, url: String) {
    try {
      val intent = Intent().setClassName(context, url)
      context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
      e.printStackTrace()
      Timber.e("Error in router activity not found!")
    }
  }
}