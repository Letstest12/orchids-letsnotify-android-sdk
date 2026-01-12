package com.letsnotify.android.network.models

import com.google.gson.annotations.SerializedName

data class DeviceRegistrationRequest(
    @SerializedName("api_key") val apiKey: String,
    @SerializedName("fcm_token") val fcmToken: String,
    @SerializedName("device_model") val deviceModel: String,
    @SerializedName("os_version") val osVersion: String,
    @SerializedName("sdk_version") val sdkVersion: String,
    @SerializedName("user_id") val userId: String? = null,
    @SerializedName("tags") val tags: Map<String, String>? = null
)

data class UserSyncRequest(
    @SerializedName("user_id") val userId: String,
    @SerializedName("tags") val tags: Map<String, String>? = null
)

data class EventTrackingRequest(
    @SerializedName("event_type") val eventType: String, // "delivered", "opened"
    @SerializedName("notification_id") val notificationId: String,
    @SerializedName("timestamp") val timestamp: Long = System.currentTimeMillis()
)

data class ApiResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String? = null
)
