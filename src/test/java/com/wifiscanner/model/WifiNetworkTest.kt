package com.wifiscanner.model

import org.junit.Assert.*
import org.junit.Test

class WifiNetworkTest {

    @Test
    fun `test signal quality for excellent signal`() {
        val network = WifiNetwork(
            ssid = "TestNetwork", 
            bssid = "00:00:00:00:00", 
            signalStrength = -40, 
            securityType = "WPA2", 
            frequency = 2400
        )
        assertEquals("Excellent", network.getSignalQuality())
    }

    @Test
    fun `test signal quality for good signal`() {
        val network = WifiNetwork(
            ssid = "TestNetwork", 
            bssid = "00:00:00:00:00", 
            signalStrength = -55, 
            securityType = "WPA2", 
            frequency = 2400
        )
        assertEquals("Good", network.getSignalQuality())
    }

    @Test
    fun `test signal quality for fair signal`() {
        val network = WifiNetwork(
            ssid = "TestNetwork", 
            bssid = "00:00:00:00:00", 
            signalStrength = -65, 
            securityType = "WPA2", 
            frequency = 2400
        )
        assertEquals("Fair", network.getSignalQuality())
    }

    @Test
    fun `test signal quality for poor signal`() {
        val network = WifiNetwork(
            ssid = "TestNetwork", 
            bssid = "00:00:00:00:00", 
            signalStrength = -80, 
            securityType = "WPA2", 
            frequency = 2400
        )
        assertEquals("Poor", network.getSignalQuality())
    }

    @Test
    fun `test toString representation`() {
        val network = WifiNetwork(
            ssid = "TestNetwork", 
            bssid = "00:00:00:00:00", 
            signalStrength = -55, 
            securityType = "WPA2", 
            frequency = 2400
        )
        assertEquals("Network: TestNetwork (Good) - WPA2", network.toString())
    }
}