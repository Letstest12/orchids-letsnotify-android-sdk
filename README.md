# LetsNotify Mobile Push Notification SDKs

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Platforms](https://img.shields.io/badge/platforms-Android%20%7C%20iOS-green.svg)

Production-ready Push Notification SDKs for the **LetsNotify** SaaS platform. Integrate advanced push notifications into your mobile apps in under 5 minutes.

## 🚀 Key Features

- **Cross-Platform**: Parity between Android and iOS SDKs for a consistent developer experience.
- **Single Entry Point**: Clean initialization with `initialize(apiKey: "...")`.
- **Automatic Token Management**: Handles APNs and FCM token registration and refresh.
- **Event Tracking**: Built-in support for `delivered`, `opened`, and `app_launch` events.
- **User Segmentation**: Easily set user IDs and custom tags for targeted campaigns.
- **Resilient & Safe**: Non-blocking network calls, defensive coding, and zero impact on app startup.

---

## 🤖 Android SDK (Kotlin)

The Android SDK is built with Kotlin and leverages Firebase Cloud Messaging (FCM).

### Quick Start
```kotlin
// module-level build.gradle
dependencies {
    implementation project(':letsnotify-sdk')
}

// In your Application class
LetsNotify.initialize(this, "YOUR_API_KEY")
LetsNotify.setUserId("user_123")
```

👉 [Android SDK Documentation](letsnotify-sdk/README.md)

---

## 🍎 iOS SDK (Swift)

The iOS SDK is built with Swift and leverages Apple Push Notification Service (APNs) via Swift Package Manager (SPM).

### Quick Start
```swift
// In your AppDelegate or SceneDelegate
LetsNotify.initialize(apiKey: "YOUR_API_KEY", launchOptions: launchOptions)
LetsNotify.setUserId("user_123")
```

### Installation
Add the `letsnotify-ios-sdk` as a dependency in your Xcode project or `Package.swift`.

👉 [iOS SDK Documentation](letsnotify-ios-sdk/README.md)

---

## 📦 Project Structure

```text
.
├── letsnotify-sdk/          # Android SDK (Kotlin)
├── letsnotify-ios-sdk/      # iOS SDK (Swift Package)
├── src/                     # Landing Page (Next.js)
└── ...
```

## 📄 License

© 2026 LetsNotify. All rights reserved.
