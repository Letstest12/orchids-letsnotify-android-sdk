package com.letsnotify.android.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.letsnotify.android.config.LetsNotifyConfig

internal class NotificationEngine(private val context: Context) {

    init {
        createDefaultChannel()
    }

    private fun createDefaultChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                LetsNotifyConfig.DEFAULT_CHANNEL_ID,
                LetsNotifyConfig.DEFAULT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
    }

    fun showNotification(title: String, body: String, data: Map<String, String>) {
        val notificationId = (System.currentTimeMillis() % 10000).toInt()
        
        // Use host app's launcher activity as intent target
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            data.forEach { (key, value) -> putExtra(key, value) }
            putExtra("notification_id", data["notification_id"] ?: "")
        }

        val pendingIntent = PendingIntent.getActivity(
            context, notificationId, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, LetsNotifyConfig.DEFAULT_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Default icon, developer should override in manifest
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, builder.build())
    }
}
