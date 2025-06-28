package com.example.wifiscanner.model

import android.net.wifi.ScanResult
import androidx.annotation.DrawableRes
import com.example.wifiscanner.R

data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val signalStrength: Int,
    val securityType: String
) {
    companion object {
        fun fromScanResult(scanResult: ScanResult): WifiNetwork {
            return WifiNetwork(
                ssid = scanResult.SSID ?: "Unknown Network",
                bssid = scanResult.BSSID ?: "",
                signalStrength = scanResult.level,
                securityType = getSecurityType(scanResult)
            )
        }

        private fun getSecurityType(scanResult: ScanResult): String {
            return when {
                scanResult.capabilities.contains("WPA3") -> "WPA3"
                scanResult.capabilities.contains("WPA2") -> "WPA2"
                scanResult.capabilities.contains("WPA") -> "WPA"
                scanResult.capabilities.contains("WEP") -> "WEP"
                else -> "Open"
            }
        }
    }

    fun getSignalStrengthText(): String {
        return when {
            signalStrength > -50 -> "Excellent"
            signalStrength > -60 -> "Strong"
            signalStrength > -70 -> "Good"
            signalStrength > -80 -> "Weak"
            else -> "Very Weak"
        }
    }

    @DrawableRes
    fun getSignalStrengthIcon(): Int {
        return when {
            signalStrength > -50 -> R.drawable.ic_wifi_signal_high
            signalStrength > -60 -> R.drawable.ic_wifi_signal_high
            signalStrength > -70 -> R.drawable.ic_wifi_signal_medium
            signalStrength > -80 -> R.drawable.ic_wifi_signal_low
            else -> R.drawable.ic_wifi_signal_none
        }
    }
}