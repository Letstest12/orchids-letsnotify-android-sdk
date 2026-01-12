import Foundation

internal class TrackingManager {
    static let shared = TrackingManager()
    
    func trackEvent(name: String, properties: [String: Any]? = nil) {
        var body: [String: Any] = [
            "event_name": name,
            "timestamp": ISO8601DateFormatter().string(from: Date()),
            "device_token": StorageManager.shared.deviceToken ?? ""
        ]
        
        if let userId = StorageManager.shared.userId {
            body["user_id"] = userId
        }
        
        if let props = properties {
            body["properties"] = props
        }
        
        NetworkClient.shared.post(endpoint: SDKConfig.Endpoints.trackEvent, body: body) { result in
            switch result {
            case .success:
                print("LetsNotify: Event '\(name)' tracked successfully")
            case .failure(let error):
                print("LetsNotify: Failed to track event '\(name)': \(error)")
            }
        }
    }
}
