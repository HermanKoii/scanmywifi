package com.example.wifiscanner

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WifiScannerActivityTest {

    @Test
    fun testInitialState() {
        val networks = listOf<WifiNetwork>()
        val adapter = WifiNetworkAdapter(networks)
        
        assertEquals(0, adapter.itemCount)
    }

    @Test
    fun testAdapterWithNetworks() {
        val networks = listOf(
            WifiNetwork(
                ssid = "TestNetwork", 
                bssid = "00:00:00:00:00", 
                signalStrength = -55, 
                securityType = "WPA2",
                frequency = 2400
            )
        )
        val adapter = WifiNetworkAdapter(networks)
        
        assertEquals(1, adapter.itemCount)
    }
}