# LetsNotify iOS Push Notification SDK (Swift)

![Platform](https://img.shields.io/badge/platform-iOS-blue.svg)
![Language](https://img.shields.io/badge/language-Swift-orange.svg)
![Package Manager](https://img.shields.io/badge/package--manager-SPM-red.svg)

The **LetsNotify iOS SDK** provides a high-performance, lightweight integration for Apple Push Notification Service (APNs).

## 📦 Installation

### Swift Package Manager (SPM)

1. In Xcode, go to **File > Add Packages...**
2. Enter the repository URL.
3. Select the `LetsNotify` package.

Alternatively, add it to your `Package.swift`:

```swift
dependencies: [
    .package(url: "...", from: "1.0.0")
]
```

## 🚀 Quick Start

### 1. Initialize the SDK

In your `AppDelegate.swift`:

```swift
import LetsNotify

func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
    // Initialize the SDK
    LetsNotify.initialize(apiKey: "YOUR_API_KEY", launchOptions: launchOptions)
    
    return true
}
```

### 2. Identify Users

```swift
LetsNotify.setUserId("user_99")
```

### 3. Add Custom Segments (Tags)

```swift
LetsNotify.setTags([
    "membership": "gold",
    "region": "europe"
])
```

## 🛠 Features

- **Automatic Registration**: Handles APNs token registration and backend synchronization.
- **Event Tracking**: Automatically tracks notification delivery and open events.
- **Background Tasks**: Supports data-only notifications and background updates.
- **Thread Safety**: All API calls are non-blocking and thread-safe.

## 📁 Module Structure

```text
LetsNotify
 ├── LetsNotify.swift        # Main SDK Interface
 ├── Config/                 # Configuration Management
 ├── Push/                   # APNs Token Handling
 ├── Notification/           # UI Presentation & Logic
 ├── Network/                # Async URLSession Client
 ├── Storage/                # Local Identifier Persistence
 ├── Tracking/               # Analytics & Event Sync
 └── Utils/                  # Internal Helpers
```

## 📄 License

© 2026 LetsNotify. All rights reserved.
