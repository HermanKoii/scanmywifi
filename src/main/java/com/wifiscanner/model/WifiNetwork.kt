package com.wifiscanner.model

import android.R

/**
 * Data class representing a WiFi network with its comprehensive attributes
 * @property ssid The network's Service Set Identifier (network name)
 * @property bssid The network's Basic Service Set Identifier (MAC address)
 * @property signalStrength The signal strength of the WiFi network in dBm
 * @property securityType The security protocol of the network (e.g., WPA2, Open)
 * @property frequency The frequency of the WiFi network in MHz
 */
data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val signalStrength: Int,
    val securityType: String,
    val frequency: Int
) {
    /**
     * Converts signal strength to a human-readable quality description
     * @return A string representation of signal strength quality
     */
    fun getSignalQuality(): String = when {
        signalStrength >= -50 -> "Excellent"
        signalStrength >= -60 -> "Good"
        signalStrength >= -70 -> "Fair"
        else -> "Poor"
    }

    /**
     * Provides a color resource based on signal strength
     * @return Resource ID for signal strength color
     */
    fun getSignalColor(): Int = when {
        signalStrength >= -50 -> R.color.holo_green_light
        signalStrength >= -60 -> R.color.holo_green_dark
        signalStrength >= -70 -> R.color.holo_orange_light
        else -> R.color.holo_red_light
    }

    /**
     * Generates a formatted display string for the WiFi network
     * @return A comprehensive string representation of the network
     */
    override fun toString(): String = 
        "Network: $ssid (${getSignalQuality()}) - $securityType"
}