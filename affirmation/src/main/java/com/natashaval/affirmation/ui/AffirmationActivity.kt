package com.natashaval.affirmation.ui

import android.app.RemoteInput
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.*
import com.natashaval.affirmation.databinding.ActivityAffirmationBinding
import com.natashaval.base.utils.setSafeClickListener
import timber.log.Timber

class AffirmationActivity : AppCompatActivity() {
  private lateinit var binding: ActivityAffirmationBinding
  private lateinit var dataClient: DataClient

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAffirmationBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val message = intent.getStringExtra(AFFIRMATION_BUNDLE)
    message?.let {
      binding.tvAffirmation.text = it
      binding.btSend.setSafeClickListener {
        sendMessage(it)
      }
    }

    val reply = getReply(intent)
    reply?.let {
      Toast.makeText(this, "Reply received: $it", Toast.LENGTH_SHORT).show()
    }

    dataClient = Wearable.getDataClient(this)
  }

  //  https://developer.android.com/training/wearables/data-layer/data-items#SyncData
  //  https://www.dashdevs.com/blog/wear-os-app-development-tutorial-p2/
  private fun sendMessage(message: String?) {
    val putDataReq: PutDataRequest = PutDataMapRequest.create("/affirmation").run {
      dataMap.putString(DATA_KEY, message)
      setUrgent()
      asPutDataRequest()
    }
    val putDataTask: Task<DataItem> = dataClient.putDataItem(putDataReq)
    putDataTask.addOnCompleteListener {
      Timber.d("Task complete")
    }.addOnSuccessListener {
      Timber.d("Task success: $it")
    }.addOnCanceledListener {
      Timber.d("Task cancel")
    }
  }

  // https://developer.android.com/training/wearables/notifications/creating#ReceiveInput
  private fun getReply(intent: Intent): CharSequence? = RemoteInput.getResultsFromIntent(intent)?.run {
    getCharSequence(EXTRA_REPLY)
  }

  companion object {
    const val AFFIRMATION_BUNDLE = "affirmation_bundle"
    const val EXTRA_REPLY = "extra_reply"
    private const val DATA_KEY = "data_key"
  }
}