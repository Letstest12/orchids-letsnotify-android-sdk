import Foundation

internal class StorageManager {
    static let shared = StorageManager()
    private let userDefaults = UserDefaults.standard
    
    private enum Keys {
        static let apiKey = "letsnotify_api_key"
        static let userId = "letsnotify_user_id"
        static let deviceToken = "letsnotify_device_token"
        static let tags = "letsnotify_tags"
    }
    
    var apiKey: String? {
        get { userDefaults.string(forKey: Keys.apiKey) }
        set { userDefaults.set(newValue, forKey: Keys.apiKey) }
    }
    
    var userId: String? {
        get { userDefaults.string(forKey: Keys.userId) }
        set { userDefaults.set(newValue, forKey: Keys.userId) }
    }
    
    var deviceToken: String? {
        get { userDefaults.string(forKey: Keys.deviceToken) }
        set { userDefaults.set(newValue, forKey: Keys.deviceToken) }
    }
    
    var tags: [String: String]? {
        get { userDefaults.dictionary(forKey: Keys.tags) as? [String: String] }
        set { userDefaults.set(newValue, forKey: Keys.tags) }
    }
}
