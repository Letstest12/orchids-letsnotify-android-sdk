package com.letsnotify.android.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.letsnotify.android.notification.NotificationEngine
import com.letsnotify.android.storage.LetsNotifyPrefs
import com.letsnotify.android.tracking.Tracker

class LetsNotifyMessagingService : FirebaseMessagingService() {

    private lateinit var tracker: Tracker
    private lateinit var notificationEngine: NotificationEngine
    private lateinit var prefs: LetsNotifyPrefs

    override fun onCreate() {
        super.onCreate()
        tracker = Tracker(applicationContext)
        notificationEngine = NotificationEngine(applicationContext)
        prefs = LetsNotifyPrefs(applicationContext)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        tracker.registerDevice(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (!prefs.pushEnabled) return

        val data = remoteMessage.data
        val notificationId = data["notification_id"] ?: return
        
        // Track delivery
        tracker.trackEvent("delivered", notificationId)

        // Render notification
        val title = data["title"] ?: remoteMessage.notification?.title ?: "Notification"
        val body = data["body"] ?: remoteMessage.notification?.body ?: ""
        
        notificationEngine.showNotification(title, body, data)
    }
}
