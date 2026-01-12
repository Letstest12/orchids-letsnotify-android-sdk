import Foundation
import UserNotifications

internal class NotificationHandler {
    static let shared = NotificationHandler()
    
    func handleForegroundNotification(_ notification: UNNotification, completion: @escaping (UNNotificationPresentationOptions) -> Void) {
        // Track delivery
        TrackingManager.shared.trackEvent(name: "notification_delivered", properties: [
            "notification_id": notification.request.identifier
        ])
        
        // Show the notification in foreground
        completion([.alert, .sound, .badge])
    }
    
    func handleNotificationResponse(_ response: UNNotificationResponse) {
        // Track open
        TrackingManager.shared.trackEvent(name: "notification_opened", properties: [
            "notification_id": response.notification.request.identifier,
            "action_id": response.actionIdentifier
        ])
    }
}
