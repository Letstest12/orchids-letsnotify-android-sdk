package com.letsnotify.android.storage

import android.content.Context
import android.content.SharedPreferences

internal class LetsNotifyPrefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "letsnotify_prefs"
        private const val KEY_API_KEY = "api_key"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_FCM_TOKEN = "fcm_token"
        private const val KEY_TAGS = "tags"
        private const val KEY_PUSH_ENABLED = "push_enabled"
    }

    var apiKey: String?
        get() = prefs.getString(KEY_API_KEY, null)
        set(value) = prefs.edit().putString(KEY_API_KEY, value).apply()

    var userId: String?
        get() = prefs.getString(KEY_USER_ID, null)
        set(value) = prefs.edit().putString(KEY_USER_ID, value).apply()

    var fcmToken: String?
        get() = prefs.getString(KEY_FCM_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_FCM_TOKEN, value).apply()

    var pushEnabled: Boolean
        get() = prefs.getBoolean(KEY_PUSH_ENABLED, true)
        set(value) = prefs.edit().putBoolean(KEY_PUSH_ENABLED, value).apply()

    fun saveTags(tags: Map<String, String>) {
        val tagsString = tags.entries.joinToString(",") { "${it.key}:${it.value}" }
        prefs.edit().putString(KEY_TAGS, tagsString).apply()
    }

    fun getTags(): Map<String, String> {
        val tagsString = prefs.getString(KEY_TAGS, null) ?: return emptyMap()
        return tagsString.split(",").filter { it.contains(":") }.associate {
            val parts = it.split(":")
            parts[0] to parts[1]
        }
    }
}
