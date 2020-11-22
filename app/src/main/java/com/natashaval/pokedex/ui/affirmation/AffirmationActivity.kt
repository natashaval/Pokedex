package com.natashaval.pokedex.ui.affirmation

import android.app.RemoteInput
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    val reply = getReply(intent)
    reply?.let {
      Toast.makeText(this, "Reply received: $it", Toast.LENGTH_SHORT).show()
    }
  }

// https://developer.android.com/training/wearables/notifications/creating#ReceiveInput
  private fun getReply(intent: Intent): CharSequence? =
    RemoteInput.getResultsFromIntent(intent)?.run {
      getCharSequence(EXTRA_REPLY)
    }

  companion object {
    const val AFFIRMATION_BUNDLE = "affirmation_bundle"
    val EXTRA_REPLY = "extra_reply"
  }
}