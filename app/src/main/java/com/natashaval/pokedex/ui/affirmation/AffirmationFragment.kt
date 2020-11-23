package com.natashaval.pokedex.ui.affirmation

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.fragment.app.viewModels
import com.natashaval.pokedex.R
import com.natashaval.pokedex.databinding.FragmentAffirmationBinding
import com.natashaval.pokedex.model.Status
import com.natashaval.pokedex.utils.*
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

    binding.btBigText.setSafeClickListener {
      val value = viewModel.affirmation.value
      if (value?.status == Status.SUCCESS && !value?.data?.affirmation.isNullOrEmpty()) {
        createBigNotification(value?.data?.affirmation)
      }
    }

    binding.btReply.setSafeClickListener {
      val value = viewModel.affirmation.value
      if (value?.status == Status.SUCCESS && !value?.data?.affirmation.isNullOrEmpty()) {
        createReplyNotification(value?.data?.affirmation)
      }
    }
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
        PendingIntent.getActivity(ctx, 0, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT)
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

  private fun createBigNotification(message: String?) {
    val notificationId = 2

    context?.let { ctx ->
      val viewPendingIntent = Intent(ctx, AffirmationActivity::class.java)
        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).let { viewIntent ->
          viewIntent.putExtra(AffirmationActivity.AFFIRMATION_BUNDLE, message)
          PendingIntent.getActivity(ctx, 0, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

      val openBlibliIntent = Intent(Intent.ACTION_VIEW).let { intent ->
        intent.data = Uri.parse("https://www.blibli.com")
        PendingIntent.getActivity(ctx, 0, intent, 0)
      }

      val notificationBuilder = NotificationCompat.Builder(ctx, CHANNEL_ID)
        .setContentTitle("Big Text: " + getString(R.string.affirmation_today))
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_hand_heart)
        .setLargeIcon(BitmapFactory.decodeResource(ctx.resources, R.mipmap.ic_pokedex_round))
        .setStyle(NotificationCompat.BigTextStyle().bigText(message + message + message))
        .addAction(R.drawable.ic_cart, "Open Blibli", openBlibliIntent)
        .setContentIntent(viewPendingIntent)
        .extend(wearableExtender)
        .build()

      notificationManager?.notify(notificationId, notificationBuilder)
    }
  }

  // https://developer.android.com/training/wearables/notifications/creating#ReceiveInput
  private fun createReplyNotification(message: String?) {
    val notificationId = 3

    context?.let { ctx ->
      val viewPendingIntent = Intent(ctx, AffirmationActivity::class.java)
        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).let { viewIntent ->
          viewIntent.putExtra(AffirmationActivity.AFFIRMATION_BUNDLE, message)
          PendingIntent.getActivity(ctx, 0, viewIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

      val openBlibliIntent = Intent(Intent.ACTION_VIEW).let { intent ->
        intent.data = Uri.parse("https://www.blibli.com")
        PendingIntent.getActivity(ctx, 0, intent, 0)
      }

      val remoteInput = RemoteInput.Builder(AffirmationActivity.EXTRA_REPLY)
        .setLabel(getString(R.string.affirmation_reply))
        .setChoices(ctx.resources.getStringArray(R.array.affirmation_choice))
        .setAllowFreeFormInput(true)
        .build()

      val action = NotificationCompat.Action.Builder(R.drawable.ic_pill, getString(R.string.affirmation_reply), viewPendingIntent)
        .addRemoteInput(remoteInput)
        .setAllowGeneratedReplies(true)
        .build()

      val notificationBuilder = NotificationCompat.Builder(ctx, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_hand_heart)
        .setContentTitle("Reply: " + getString(R.string.affirmation_today))
        .setContentText(message)
        .setVibrate(longArrayOf(1000, 500, 500))
        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
        .extend(NotificationCompat.WearableExtender().addAction(action))
        .build()

      notificationManager?.notify(notificationId, notificationBuilder)
    }
  }
}