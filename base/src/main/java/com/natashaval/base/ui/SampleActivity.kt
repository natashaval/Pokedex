package com.natashaval.base.ui

import android.app.Activity
import android.os.Bundle
import com.natashaval.base.R

class SampleActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sample)
  }
}