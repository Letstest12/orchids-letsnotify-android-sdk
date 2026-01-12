import Foundation

internal enum NetworkError: Error {
    case invalidUrl
    case noData
    case unauthorized
    case serverError(Int)
    case encodingError
}

internal class NetworkClient {
    static let shared = NetworkClient()
    
    func post(endpoint: String, body: [String: Any], completion: @escaping (Result<Data, NetworkError>) -> Void) {
        guard let url = URL(string: SDKConfig.baseUrl + endpoint) else {
            completion(.failure(.invalidUrl))
            return
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        if let apiKey = StorageManager.shared.apiKey {
            request.setValue(apiKey, forHTTPHeaderField: "X-API-Key")
        }
        
        do {
            request.httpBody = try JSONSerialization.data(withJSONObject: body)
        } catch {
            completion(.failure(.encodingError))
            return
        }
        
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if let httpResponse = response as? HTTPURLResponse {
                if httpResponse.statusCode == 401 {
                    completion(.failure(.unauthorized))
                    return
                }
                if !(200...299).contains(httpResponse.statusCode) {
                    completion(.failure(.serverError(httpResponse.statusCode)))
                    return
                }
            }
            
            guard let data = data else {
                completion(.failure(.noData))
                return
            }
            
            completion(.success(data))
        }
        task.resume()
    }
}
