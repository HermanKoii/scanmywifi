package com.example.wifiscanner

import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class MainActivityUnitTest {

    @Test
    fun `test activity components initialization`() {
        val activity = MainActivity()
        activity.onCreate(null)

        // Verify key UI components are initialized
        assertNotNull(activity.findViewById<MaterialButton>(R.id.btn_scan_networks))
        assertNotNull(activity.findViewById<RecyclerView>(R.id.rv_wifi_networks))
        assertNotNull(activity.findViewById<TextView>(R.id.tv_no_networks))
    }
}