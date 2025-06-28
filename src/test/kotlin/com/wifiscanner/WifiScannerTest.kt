package com.wifiscanner

import org.junit.Assert.*
import org.junit.Test

class WifiScannerTest {

    @Test
    fun `test wifi network creation`() {
        val network = WifiNetwork(
            ssid = "TestNetwork", 
            bssid = "00:11:22:33:44:55", 
            signalStrength = -55, 
            securityType = "WPA2", 
            frequency = 2400
        )

        assertEquals("TestNetwork", network.ssid)
        assertEquals("00:11:22:33:44:55", network.bssid)
        assertEquals(-55, network.signalStrength)
        assertEquals("WPA2", network.securityType)
        assertEquals(2400, network.frequency)
    }

    @Test
    fun `test signal quality calculation`() {
        val excellentNetwork = WifiNetwork(
            ssid = "ExcellentNetwork", 
            bssid = "00:11:22:33:44:55", 
            signalStrength = -40, 
            securityType = "WPA2", 
            frequency = 2400
        )

        val goodNetwork = WifiNetwork(
            ssid = "GoodNetwork", 
            bssid = "00:11:22:33:44:55", 
            signalStrength = -55, 
            securityType = "WPA2", 
            frequency = 2400
        )

        val fairNetwork = WifiNetwork(
            ssid = "FairNetwork", 
            bssid = "00:11:22:33:44:55", 
            signalStrength = -65, 
            securityType = "WPA2", 
            frequency = 2400
        )

        val poorNetwork = WifiNetwork(
            ssid = "PoorNetwork", 
            bssid = "00:11:22:33:44:55", 
            signalStrength = -80, 
            securityType = "WPA2", 
            frequency = 2400
        )

        assertEquals("Excellent", excellentNetwork.getSignalQuality())
        assertEquals("Good", goodNetwork.getSignalQuality())
        assertEquals("Fair", fairNetwork.getSignalQuality())
        assertEquals("Poor", poorNetwork.getSignalQuality())
    }
}