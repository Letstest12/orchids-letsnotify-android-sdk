import Foundation
import UIKit
import UserNotifications

@objc public final class LetsNotify: NSObject {
    
    @objc public static let shared = LetsNotify()
    
    private override init() {
        super.init()
    }
    
    /// Initializes the LetsNotify SDK with your API key.
    /// - Parameters:
    ///   - apiKey: Your public API key from the LetsNotify dashboard.
    ///   - launchOptions: The launch options passed from AppDelegate.
    @objc public static func initialize(apiKey: String, launchOptions: [UIApplication.LaunchOptionsKey: Any]?) {
        StorageManager.shared.apiKey = apiKey
        
        // Handle notification launch
        if let notification = launchOptions?[.remoteNotification] as? [AnyHashable: Any] {
            TrackingManager.shared.trackEvent(name: "app_launch_via_push", properties: [
                "notification": notification
            ])
        }
        
        print("LetsNotify: SDK initialized successfully")
    }
    
    /// Sets the user identifier for the current device.
    /// - Parameter userId: A unique identifier for the user.
    @objc public static func setUserId(_ userId: String) {
        StorageManager.shared.userId = userId
        syncUser()
    }
    
    /// Sets custom tags for the current user for segmentation.
    /// - Parameter tags: A dictionary of key-value pairs.
    @objc public static func setTags(_ tags: [String: String]) {
        StorageManager.shared.tags = tags
        syncUser()
    }
    
    /// Enables push notifications by requesting permission and registering for remote notifications.
    @objc public static func enablePush() {
        PushManager.shared.registerForPush()
    }
    
    /// Disables push notifications for the current user.
    @objc public static func disablePush() {
        // Implementation for disabling push on backend if needed
        TrackingManager.shared.trackEvent(name: "push_disabled")
    }
    
    // MARK: - Internal Helpers
    
    private static func syncUser() {
        guard let apiKey = StorageManager.shared.apiKey else { return }
        
        var body: [String: Any] = [:]
        if let userId = StorageManager.shared.userId { body["user_id"] = userId }
        if let tags = StorageManager.shared.tags { body["tags"] = tags }
        
        NetworkClient.shared.post(endpoint: SDKConfig.Endpoints.syncUser, body: body) { _ in }
    }
    
    // MARK: - AppDelegate Hooks
    
    @objc public static func didRegisterForRemoteNotificationsWithDeviceToken(_ deviceToken: Data) {
        PushManager.shared.handleDeviceToken(deviceToken)
    }
    
    @objc public static func didFailToRegisterForRemoteNotificationsWithError(_ error: Error) {
        print("LetsNotify: Failed to register for remote notifications: \(error.localizedDescription)")
    }
}
