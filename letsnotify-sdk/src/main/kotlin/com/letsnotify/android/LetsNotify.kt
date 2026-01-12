package com.letsnotify.android

import android.content.Context
import com.google.firebase.messaging.FirebaseMessaging
import com.letsnotify.android.storage.LetsNotifyPrefs
import com.letsnotify.android.tracking.Tracker

object LetsNotify {
    private var isInitialized = false
    private lateinit var prefs: LetsNotifyPrefs
    private lateinit var tracker: Tracker

    fun initialize(context: Context, apiKey: String) {
        if (isInitialized) return
        
        val appContext = context.applicationContext
        prefs = LetsNotifyPrefs(appContext)
        tracker = Tracker(appContext)
        
        prefs.apiKey = apiKey
        isInitialized = true

        // Initial token fetch and registration
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { token ->
                    tracker.registerDevice(token)
                }
            }
        }
    }

    fun setUserId(userId: String) {
        checkInitialized()
        tracker.syncUser(userId)
    }

    fun setTags(tags: Map<String, String>) {
        checkInitialized()
        tracker.syncUser(prefs.userId ?: "", tags)
    }

    fun enablePush() {
        checkInitialized()
        prefs.pushEnabled = true
    }

    fun disablePush() {
        checkInitialized()
        prefs.pushEnabled = false
    }

    private fun checkInitialized() {
        if (!isInitialized) {
            throw IllegalStateException("LetsNotify SDK must be initialized before use.")
        }
    }
}
