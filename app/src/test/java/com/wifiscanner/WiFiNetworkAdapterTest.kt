package com.wifiscanner

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wifiscanner.adapter.WiFiNetworkAdapter
import com.wifiscanner.model.WiFiNetwork
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
@Config(sdk = [29])
class WiFiNetworkAdapterTest {

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `WiFiNetworkAdapter should correctly create view holder`() {
        val networks = listOf(
            WiFiNetwork("TestNetwork1", "00:11:22:33:44:55", -65, WiFiNetwork.SecurityType.WPA2),
            WiFiNetwork("TestNetwork2", "AA:BB:CC:DD:EE:FF", -75, WiFiNetwork.SecurityType.WPA3)
        )
        
        val adapter = WiFiNetworkAdapter(networks)
        
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `WiFiNetwork model should store security type correctly`() {
        val network = WiFiNetwork("TestNetwork", "00:11:22:33:44:55", -65, WiFiNetwork.SecurityType.WPA2)
        
        assertEquals("TestNetwork", network.ssid)
        assertEquals(WiFiNetwork.SecurityType.WPA2, network.securityType)
        assertEquals(-65, network.signalStrength)
    }

    @Test
    fun `WiFiNetwork security types should be defined`() {
        val securityTypes = WiFiNetwork.SecurityType.values()
        
        assertNotNull(securityTypes)
        assertEquals(6, securityTypes.size)
    }
}