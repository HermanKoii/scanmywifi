package com.example.wifiscanner

import android.net.wifi.ScanResult
import com.example.wifiscanner.model.WifiNetwork
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WifiNetworkTest {

    @Mock
    private lateinit var mockScanResult: ScanResult

    @Test
    fun `test signal strength text mapping`() {
        val excellentSignal = WifiNetwork("Test", "00:00:00", -50, "WPA2")
        val strongSignal = WifiNetwork("Test", "00:00:00", -60, "WPA2")
        val goodSignal = WifiNetwork("Test", "00:00:00", -70, "WPA2")
        val weakSignal = WifiNetwork("Test", "00:00:00", -80, "WPA2")
        val veryWeakSignal = WifiNetwork("Test", "00:00:00", -90, "WPA2")

        assertEquals("Excellent", excellentSignal.getSignalStrengthText())
        assertEquals("Strong", strongSignal.getSignalStrengthText())
        assertEquals("Good", goodSignal.getSignalStrengthText())
        assertEquals("Weak", weakSignal.getSignalStrengthText())
        assertEquals("Very Weak", veryWeakSignal.getSignalStrengthText())
    }

    @Test
    fun `test security type detection`() {
        `when`(mockScanResult.SSID).thenReturn("TestNetwork")
        `when`(mockScanResult.BSSID).thenReturn("00:11:22:33:44:55")
        `when`(mockScanResult.level).thenReturn(-60)

        // Test WPA2
        `when`(mockScanResult.capabilities).thenReturn("[WPA2-PSK-CCMP]")
        val wpa2Network = WifiNetwork.fromScanResult(mockScanResult)
        assertEquals("WPA2", wpa2Network.securityType)

        // Test Open network
        `when`(mockScanResult.capabilities).thenReturn("")
        val openNetwork = WifiNetwork.fromScanResult(mockScanResult)
        assertEquals("Open", openNetwork.securityType)

        // Test additional security types
        `when`(mockScanResult.capabilities).thenReturn("[WPA3-SAE-CCMP]")
        val wpa3Network = WifiNetwork.fromScanResult(mockScanResult)
        assertEquals("WPA3", wpa3Network.securityType)
    }

    @Test
    fun `test signal strength icon mapping`() {
        val excellentSignal = WifiNetwork("Test", "00:00:00", -50, "WPA2")
        val strongSignal = WifiNetwork("Test", "00:00:00", -60, "WPA2")
        val goodSignal = WifiNetwork("Test", "00:00:00", -70, "WPA2")
        val weakSignal = WifiNetwork("Test", "00:00:00", -80, "WPA2")
        val veryWeakSignal = WifiNetwork("Test", "00:00:00", -90, "WPA2")

        assertEquals(com.example.wifiscanner.R.drawable.ic_wifi_signal_high, excellentSignal.getSignalStrengthIcon())
        assertEquals(com.example.wifiscanner.R.drawable.ic_wifi_signal_high, strongSignal.getSignalStrengthIcon())
        assertEquals(com.example.wifiscanner.R.drawable.ic_wifi_signal_medium, goodSignal.getSignalStrengthIcon())
        assertEquals(com.example.wifiscanner.R.drawable.ic_wifi_signal_low, weakSignal.getSignalStrengthIcon())
        assertEquals(com.example.wifiscanner.R.drawable.ic_wifi_signal_none, veryWeakSignal.getSignalStrengthIcon())
    }
}