package com.natashaval.pokedex.utils

import android.view.View
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

/**
 * Created by natasha.santoso on 18/10/20.
 */

fun View.showView() {
  visibility = View.VISIBLE
}

fun View.hideView() {
  visibility = View.GONE
}

fun View.setSafeClickListener(debounceMillis: Long = 500L, function: () -> Unit) {
  this.clicks().throttleFirst(debounceMillis, TimeUnit.MILLISECONDS).subscribe {
    function()
  }
}