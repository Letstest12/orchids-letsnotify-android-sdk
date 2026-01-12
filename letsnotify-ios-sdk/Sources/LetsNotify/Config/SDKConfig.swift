import Foundation

internal struct SDKConfig {
    static let version = "1.0.0"
    static let baseUrl = "https://api.letsnotify.io/v1"
    
    struct Endpoints {
        static let registerDevice = "/devices/register"
        static let syncUser = "/users/sync"
        static let trackEvent = "/events/track"
    }
}
