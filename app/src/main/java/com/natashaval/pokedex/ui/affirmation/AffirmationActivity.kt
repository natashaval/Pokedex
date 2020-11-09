package com.natashaval.pokedex.ui.affirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ActivityAffirmationBinding

class AffirmationActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAffirmationBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAffirmationBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val message = intent.getStringExtra(AFFIRMATION_BUNDLE)
    message?.let {
      binding.tvAffirmation.text = it
    }
  }

  companion object {
    const val AFFIRMATION_BUNDLE = "affirmation_bundle"
  }
}