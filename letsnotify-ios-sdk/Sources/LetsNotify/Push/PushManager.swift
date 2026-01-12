import Foundation
import UserNotifications
import UIKit

internal class PushManager: NSObject {
    static let shared = PushManager()
    
    func registerForPush() {
        let center = UNUserNotificationCenter.current()
        center.requestAuthorization(options: [.alert, .sound, .badge]) { granted, error in
            if granted {
                DispatchQueue.main.async {
                    UIApplication.shared.registerForRemoteNotifications()
                }
            }
        }
    }
    
    func handleDeviceToken(_ deviceToken: Data) {
        let tokenParts = deviceToken.map { data in String(format: "%02.2hhx", data) }
        let token = tokenParts.joined()
        StorageManager.shared.deviceToken = token
        
        registerDeviceWithBackend(token: token)
    }
    
    private func registerDeviceWithBackend(token: String) {
        var body: [String: Any] = [
            "device_token": token,
            "platform": "ios",
            "sdk_version": SDKConfig.version
        ]
        
        if let userId = StorageManager.shared.userId {
            body["user_id"] = userId
        }
        
        if let tags = StorageManager.shared.tags {
            body["tags"] = tags
        }
        
        NetworkClient.shared.post(endpoint: SDKConfig.Endpoints.registerDevice, body: body) { result in
            switch result {
            case .success:
                print("LetsNotify: Device registered successfully")
            case .failure(let error):
                print("LetsNotify: Failed to register device: \(error)")
            }
        }
    }
}
