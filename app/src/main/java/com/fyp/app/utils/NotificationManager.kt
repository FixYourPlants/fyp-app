package com.fyp.app.utils

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.fyp.app.MainActivity
import com.fyp.app.data.api.NotificationServiceImp
import com.fyp.app.data.model.db.Notification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object NotificationManager {
    private const val DEFAULT_CHANNEL_ID = "myChannel"
    private const val DEFAULT_CHANNEL_NAME = "MySuperChannel"
    private const val DEFAULT_CHANNEL_DESCRIPTION = "SUSCRIBETE"
    const val PERMISSION_REQUEST_CODE = 1001

    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun initialize(context: Context) {
        createChannel(
            context,
            DEFAULT_CHANNEL_ID,
            DEFAULT_CHANNEL_NAME,
            DEFAULT_CHANNEL_DESCRIPTION
        )
        requestNotificationPermission(context)
    }

    private fun createChannel(
        context: Context,
        channelId: String,
        channelName: String,
        channelDescription: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = channelDescription
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun requestNotificationPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    fun createSimpleNotification(context: Context, title: String, content: String) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermission(context)
            return
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val builder = NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(content)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(
                System.currentTimeMillis().toInt(),
                builder.build()
            ) // Use a unique ID for each notification
        }
    }

    private fun readNotifications(): List<Notification> = runBlocking {
        val now = System.currentTimeMillis()
        val halfHourLater = now + TimeUnit.MINUTES.toMillis(30)

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val currentDateTime = sdf.format(Date(now))
        val halfHourLaterDateTime = sdf.format(Date(halfHourLater))

        NotificationServiceImp.getInstance().getNotifications(currentDateTime, halfHourLaterDateTime)
    }


    fun scheduleNotificationTask(
        interval: Long,
        timeUnit: TimeUnit,
        context: Context,
    ) {
        scheduler.scheduleWithFixedDelay({
            if (UserPreferencesImp.isAuthenticated()) {
                scope.launch {
                    val notifications = readNotifications()
                    for (notification in notifications)
                        createSimpleNotification(context, notification.title, notification.content)
                }
            }
        }, 0, interval, timeUnit)
    }

    fun stopNotificationTask() {
        scheduler.shutdown()
        scope.cancel()
    }
}



