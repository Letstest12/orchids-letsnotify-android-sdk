package com.letsnotify.android.tracking

import android.content.Context
import android.os.Build
import com.letsnotify.android.config.LetsNotifyConfig
import com.letsnotify.android.network.ApiService
import com.letsnotify.android.network.models.DeviceRegistrationRequest
import com.letsnotify.android.network.models.EventTrackingRequest
import com.letsnotify.android.network.models.UserSyncRequest
import com.letsnotify.android.storage.LetsNotifyPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class Tracker(private val context: Context) {
    private val prefs = LetsNotifyPrefs(context)
    private val apiService = ApiService()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun registerDevice(fcmToken: String) {
        val apiKey = prefs.apiKey ?: return
        prefs.fcmToken = fcmToken
        
        scope.launch {
            try {
                val request = DeviceRegistrationRequest(
                    apiKey = apiKey,
                    fcmToken = fcmToken,
                    deviceModel = Build.MODEL,
                    osVersion = Build.VERSION.RELEASE,
                    sdkVersion = LetsNotifyConfig.SDK_VERSION,
                    userId = prefs.userId,
                    tags = prefs.getTags()
                )
                apiService.api.registerDevice(apiKey, request)
            } catch (e: Exception) {
                // Fail silently as per requirements
            }
        }
    }

    fun syncUser(userId: String, tags: Map<String, String>? = null) {
        val apiKey = prefs.apiKey ?: return
        prefs.userId = userId
        tags?.let { prefs.saveTags(it) }

        scope.launch {
            try {
                val request = UserSyncRequest(userId, tags ?: prefs.getTags())
                apiService.api.syncUser(apiKey, request)
            } catch (e: Exception) {
                // Fail silently
            }
        }
    }

    fun trackEvent(eventType: String, notificationId: String) {
        val apiKey = prefs.apiKey ?: return
        
        scope.launch {
            try {
                val request = EventTrackingRequest(eventType, notificationId)
                apiService.api.trackEvent(apiKey, request)
            } catch (e: Exception) {
                // Fail silently
            }
        }
    }
}
