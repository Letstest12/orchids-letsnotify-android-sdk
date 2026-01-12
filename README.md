# LetsNotify Android Push Notification SDK

![Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![Language](https://img.shields.io/badge/language-Kotlin-purple.svg)

A production-ready Android Push Notification SDK for the **LetsNotify** SaaS platform. This SDK enables developers to integrate advanced push notifications with minimal effort while maintaining full control over delivery, tracking, and segmentation.

## 🚀 Key Features

- **Single Entry Point**: Easy-to-use API with `LetsNotify.initialize()`.
- **Automatic FCM Management**: Handles token generation, registration, and refresh automatically.
- **Rich Notification Support**: Custom notification engine with channel support (Android 8+).
- **Event Tracking**: Automatic `delivered` and `opened` event tracking for campaign analytics.
- **Dynamic Segmentation**: Support for user identification and custom tagging.
- **Robust Networking**: Built with Retrofit and OkHttp with async, non-blocking operations.
- **Defensive Design**: Zero-crash philosophy with silent failure handling and defensive null checks.

## 📦 SDK Structure

```text
com.letsnotify.android
 ├── LetsNotify.kt         # Public SDK API
 ├── config/               # SDK Configuration
 ├── firebase/             # FCM Service & Logic
 ├── notification/         # Rendering Engine
 ├── network/              # Retrofit API Client
 ├── storage/              # Local Persistence
 ├── tracking/             # Device & Event Sync
 └── utils/                # Shared Helpers
```

## 🛠 Integration

For detailed integration steps, please refer to the [SDK Documentation](letsnotify-sdk/README.md) or visit our [Web Preview](https://localhost:3000).

```kotlin
// Quick Start
LetsNotify.initialize(context, "ln_prod_apiKey")
LetsNotify.setUserId("user_123")
```

## 📄 License

© 2026 LetsNotify. All rights reserved.
