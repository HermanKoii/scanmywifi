package com.example.wifiscanner

import com.example.wifiscanner.model.WifiNetwork
import org.junit.Assert.*
import org.junit.Test

class WifiNetworkTest {

    @Test
    fun `test signal strength text mapping`() {
        val excellentSignal = WifiNetwork(\"Test\", \"00:00:00\", -40, \"WPA2\")
        val strongSignal = WifiNetwork(\"Test\", \"00:00:00\", -55, \"WPA2\")
        val goodSignal = WifiNetwork(\"Test\", \"00:00:00\", -65, \"WPA2\")
        val weakSignal = WifiNetwork(\"Test\", \"00:00:00\", -75, \"WPA2\")
        val veryWeakSignal = WifiNetwork(\"Test\", \"00:00:00\", -90, \"WPA2\")

        assertEquals(\"Excellent\", excellentSignal.getSignalStrengthText())
        assertEquals(\"Strong\", strongSignal.getSignalStrengthText())
        assertEquals(\"Good\", goodSignal.getSignalStrengthText())
        assertEquals(\"Weak\", weakSignal.getSignalStrengthText())
        assertEquals(\"Very Weak\", veryWeakSignal.getSignalStrengthText())
    }

    @Test
    fun `test security type detection`() {
        // Mock a scan result-like object for testing
        val mockScanResult = object {
            val SSID = \"TestNetwork\"
            val BSSID = \"00:11:22:33:44:55\"
            val level = -60
            val capabilities = \"[WPA2-PSK-CCMP]\"
        }

        // Test WPA2 network
        val wpa2Network = WifiNetwork(
            mockScanResult.SSID, 
            mockScanResult.BSSID, 
            mockScanResult.level, 
            WifiNetwork.Companion.getSecurityType(mockScanResult)
        )
        assertEquals(\"WPA2\", wpa2Network.securityType)

        // Test Open network
        val openNetworkScanResult = object {
            val SSID = \"OpenNetwork\"
            val BSSID = \"00:11:22:33:44:66\"
            val level = -70
            val capabilities = \"\"
        }

        val openNetwork = WifiNetwork(
            openNetworkScanResult.SSID, 
            openNetworkScanResult.BSSID, 
            openNetworkScanResult.level, 
            WifiNetwork.Companion.getSecurityType(openNetworkScanResult)
        )
        assertEquals(\"Open\", openNetwork.securityType)
    }
}