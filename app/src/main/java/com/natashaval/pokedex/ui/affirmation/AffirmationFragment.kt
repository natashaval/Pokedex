package com.natashaval.pokedex.ui.affirmation

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.viewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentAffirmationBinding
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.utils.hideView
import com.natashaval.pokedex.utils.setSafeClickListener
import com.natashaval.pokedex.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AffirmationFragment : Fragment() {

  companion object {
    fun newInstance() = AffirmationFragment()
    const val CHANNEL_ID = "affirmation_channel"
  }

  private var _binding: FragmentAffirmationBinding? = null
  private val binding get() = _binding!!
  private val viewModel: AffirmationViewModel by viewModels()

  private var notificationManager: NotificationManagerCompat? = null
  private var wearableExtender: NotificationCompat.WearableExtender? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentAffirmationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    notificationManager = NotificationManagerCompat.from(requireContext())
    wearableExtender = NotificationCompat.WearableExtender()

    binding.btAffirmation.setSafeClickListener { viewModel.getAffirmation() }
    viewModel.affirmation.observe(viewLifecycleOwner, {
      when (it.status) {
        Status.SUCCESS -> {
          binding.pbLoading.hideView()
          binding.tvAffirmation.text = it.data?.affirmation
          createNotification(it.data?.affirmation)
        }
        Status.LOADING -> binding.pbLoading.showView()
        Status.ERROR, Status.FAILED -> {
          binding.pbLoading.hideView()
          binding.tvAffirmation.text = getString(R.string.affirmation_error)
        }
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  //  https://developer.android.com/training/wearables/notifications/creating#kotlin
  private fun createNotification(message: String?) {
    val notificationId = 1

    context?.let { ctx ->
      // to open certain Activity after clicking notification
      val viewPendingIntent = Intent(ctx, AffirmationActivity::class.java)
          .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).let { viewIntent ->
        viewIntent.putExtra(AffirmationActivity.AFFIRMATION_BUNDLE, message)
        PendingIntent.getActivity(ctx, 0, viewIntent, 0)
      }

      val notificationBuilder = NotificationCompat.Builder(ctx, CHANNEL_ID)
          .setSmallIcon(R.drawable.ic_hand_heart)
          .setContentTitle(getString(R.string.affirmation_today))
          .setContentText(message)
          .setContentIntent(viewPendingIntent)
          .setVibrate(longArrayOf(1000, 1000, 1000))
          .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
          .extend(wearableExtender)
          .build()

      notificationManager?.notify(notificationId, notificationBuilder)
    }
  }
}