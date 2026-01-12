// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "LetsNotify",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "LetsNotify",
            targets: ["LetsNotify"]),
    ],
    dependencies: [],
    targets: [
        .target(
            name: "LetsNotify",
            dependencies: []),
        .testTarget(
            name: "LetsNotifyTests",
            dependencies: ["LetsNotify"]),
    ]
)
