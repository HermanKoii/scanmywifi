package com.wifiscanner.model

/**
 * Represents a WiFi network with detailed information including security type.
 *
 * @property ssid The name of the WiFi network
 * @property bssid The MAC address of the WiFi access point
 * @property signalStrength Signal strength in dBm
 * @property securityType Type of network security
 */
data class WiFiNetwork(
    val ssid: String,
    val bssid: String,
    val signalStrength: Int,
    val securityType: SecurityType
) {
    /**
     * Enum representing different WiFi security types.
     */
    enum class SecurityType {
        OPEN,       // No security
        WEP,        // Wired Equivalent Privacy (legacy)
        WPA,        // Wi-Fi Protected Access
        WPA2,       // Wi-Fi Protected Access 2
        WPA3,       // Wi-Fi Protected Access 3
        UNKNOWN     // Unable to determine security type
    }
}