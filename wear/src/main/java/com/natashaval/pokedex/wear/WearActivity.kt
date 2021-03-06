package com.natashaval.pokedex.wear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import com.google.android.gms.wearable.*
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.ActivityWearBinding

class WearActivity : WearableActivity(), DataClient.OnDataChangedListener {

  private lateinit var binding: ActivityWearBinding
  private val DATA_KEY = "data_key"
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityWearBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // Enables Always-on
    setAmbientEnabled()
  }

  // Wearable API clients, such as DataClient and MessageClient, are inexpensive to create and don't need to be created only once and held onto.
//  https://developer.android.com/training/wearables/data-layer/accessing
  override fun onResume() {
    super.onResume()
    Wearable.getDataClient(this).addListener(this)
  }

  override fun onPause() {
    super.onPause()
    Wearable.getDataClient(this).removeListener(this)
  }

//  https://developer.android.com/training/wearables/data-layer/events
  override fun onDataChanged(dataEvents: DataEventBuffer) {
    dataEvents.forEach { event ->
      if (event.type == DataEvent.TYPE_CHANGED) {
        Log.d("WearActivity", "Data item changed: " + event.dataItem.uri)
        event.dataItem.also {item ->
          if (item.uri.path?.compareTo("/affirmation") == 0) {
            DataMapItem.fromDataItem(item).dataMap.apply {
              binding.tvAffirmation.text = getString(DATA_KEY)
            }
          }
        }

      } else if (event.type == DataEvent.TYPE_DELETED) {
        Log.d("WearActivity", "Data item deleted: " + event.dataItem.uri)
        binding.tvAffirmation.text = getString(R.string.affirmation_error)
      }
    }
  }
}
