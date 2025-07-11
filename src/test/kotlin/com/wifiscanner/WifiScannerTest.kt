package com.wifiscanner

import android.content.Context
import android.net.wifi.WifiManager
import android.net.wifi.ScanResult
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class WifiScannerTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var wifiScanner: WifiScanner

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        // Mock context to return mock WiFi manager
        `when`(mockContext.applicationContext).thenReturn(mockContext)
        `when`(mockContext.getSystemService(Context.WIFI_SERVICE)).thenReturn(mockWifiManager)
    }

    @Test
    fun `test wifi disabled throws exception`() {
        // Arrange
        `when`(mockWifiManager.isWifiEnabled).thenReturn(false)
        wifiScanner = WifiScanner(mockContext)

        // Act & Assert
        val exception = assertFailsWith<IllegalStateException> {
            wifiScanner.scanWifiNetworks()
        }
        assertEquals("WiFi is not enabled", exception.message)
    }

    @Test
    fun `test successful wifi scan`() {
        // Arrange
        `when`(mockWifiManager.isWifiEnabled).thenReturn(true)
        `when`(mockWifiManager.startScan()).thenReturn(true)
        
        val mockScanResults = listOf(
            createMockScanResult("TestNetwork1", "00:11:22:33:44:55", -55, "WPA2"),
            createMockScanResult("TestNetwork2", "66:77:88:99:AA:BB", -65, "WPA3")
        )
        `when`(mockWifiManager.scanResults).thenReturn(mockScanResults)

        wifiScanner = WifiScanner(mockContext)

        // Act
        val networks = wifiScanner.scanWifiNetworks()

        // Assert
        assertEquals(2, networks.size)
        assertEquals("TestNetwork1", networks[0].ssid)
        assertEquals("00:11:22:33:44:55", networks[0].bssid)
        assertEquals(SecurityType.WPA2, networks[0].securityType)
    }

    @Test
    fun `test security type parsing`() {
        // Arrange
        `when`(mockWifiManager.isWifiEnabled).thenReturn(true)
        `when`(mockWifiManager.startScan()).thenReturn(true)
        
        val testCases = listOf(
            "WPA3" to SecurityType.WPA3,
            "WPA2" to SecurityType.WPA2,
            "WPA" to SecurityType.WPA,
            "WEP" to SecurityType.WEP,
            "" to SecurityType.OPEN
        )

        testCases.forEach { (capabilities, expectedType) ->
            val mockScanResult = createMockScanResult("TestNetwork", "00:11:22:33:44:55", -55, capabilities)
            `when`(mockWifiManager.scanResults).thenReturn(listOf(mockScanResult))

            wifiScanner = WifiScanner(mockContext)

            // Act
            val networks = wifiScanner.scanWifiNetworks()

            // Assert
            assertEquals(expectedType, networks[0].securityType, "Failed for capabilities: $capabilities")
        }
    }

    // Helper method to create mock ScanResult
    private fun createMockScanResult(
        ssid: String, 
        bssid: String, 
        level: Int, 
        capabilities: String
    ): ScanResult {
        val scanResult = mock(ScanResult::class.java)
        scanResult.SSID = ssid
        scanResult.BSSID = bssid
        scanResult.level = level
        scanResult.capabilities = capabilities
        return scanResult
    }
}