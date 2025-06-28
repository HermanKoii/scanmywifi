package com.example.wifiscanner

import android.os.Build
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun `activity should launch successfully`() {
        scenario.onActivity { activity ->
            assert(activity != null) { "Activity should not be null" }
        }
    }

    @Test
    fun `scan networks button should be initialized`() {
        scenario.onActivity { activity ->
            val btnScanNetworks = activity.findViewById<MaterialButton>(R.id.btn_scan_networks)
            assert(btnScanNetworks != null) { "Scan networks button should be initialized" }
            assert(btnScanNetworks.text.toString() == "Scan Networks") { "Button text should match" }
        }
    }

    @Test
    fun `wifi networks recycler view should be initialized`() {
        scenario.onActivity { activity ->
            val rvWifiNetworks = activity.findViewById<RecyclerView>(R.id.rv_wifi_networks)
            assert(rvWifiNetworks != null) { "WiFi networks RecyclerView should be initialized" }
            assert(rvWifiNetworks.layoutManager != null) { "RecyclerView should have a layout manager" }
        }
    }
}