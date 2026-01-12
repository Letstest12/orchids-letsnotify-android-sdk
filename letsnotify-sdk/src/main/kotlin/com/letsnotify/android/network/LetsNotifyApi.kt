package com.letsnotify.android.network

import com.letsnotify.android.network.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

internal interface LetsNotifyApi {
    @POST("devices/register")
    suspend fun registerDevice(
        @Header("Authorization") apiKey: String,
        @Body request: DeviceRegistrationRequest
    ): Response<ApiResponse>

    @POST("users/sync")
    suspend fun syncUser(
        @Header("Authorization") apiKey: String,
        @Body request: UserSyncRequest
    ): Response<ApiResponse>

    @POST("events/track")
    suspend fun trackEvent(
        @Header("Authorization") apiKey: String,
        @Body request: EventTrackingRequest
    ): Response<ApiResponse>
}
