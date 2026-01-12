# LetsNotify Android SDK

A production-ready Android Push Notification SDK for the LetsNotify platform.

## Features

- **Minimal Integration**: Initialize with a single line of code.
- **FCM Integration**: Automated Firebase Cloud Messaging token management.
- **Event Tracking**: Automatic tracking of notification delivery and opens.
- **User Segmentation**: Easily set user IDs and custom tags for targeted messaging.
- **Notification Channels**: Built-in support for Android 8.0+ notification channels.
- **Defensive Design**: Asynchronous, non-blocking, and silent failures.

## Installation

### 1. Add Dependencies

Add the SDK to your `build.gradle` (module-level):

```gradle
dependencies {
    implementation project(':letsnotify-sdk')
    
    // Ensure you have Firebase initialized in your project
    implementation platform('com.google.firebase:firebase-bom:32.7.0')
    implementation 'com.google.firebase:firebase-messaging-ktx'
}
```

### 2. Configure Firebase

Follow the standard Firebase Android setup (add `google-services.json` and the Google Services plugin).

### 3. Initialize the SDK

Initialize LetsNotify in your `Application` class:

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LetsNotify.initialize(this, "YOUR_API_KEY")
    }
}
```

## Usage

### Identify Users

Connect notifications to your internal user IDs:

```kotlin
LetsNotify.setUserId("user_12345")
```

### Segment with Tags

Add custom metadata for advanced targeting:

```kotlin
val tags = mapOf("plan" to "premium", "interests" to "tech,sports")
LetsNotify.setTags(tags)
```

### Control Push Notifications

Enable or disable notifications at runtime:

```kotlin
LetsNotify.disablePush()
LetsNotify.enablePush()
```

## License

LetsNotify SDK is proprietary. All rights reserved.
