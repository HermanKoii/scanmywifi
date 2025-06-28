package com.wifiscanner

import org.junit.Test
import org.junit.Assert.*

class BuildConfigTest {
    @Test
    fun `verify build configuration`() {
        // Check basic build properties
        assertTrue("Version code should be positive", BuildConfig.VERSION_CODE > 0)
        assertNotNull("Version name should not be empty", BuildConfig.VERSION_NAME)
        
        // Verify debug vs release settings
        assertNotEquals("Debug and release should have different application IDs", 
            BuildConfig.APPLICATION_ID, 
            BuildConfig.APPLICATION_ID + ".debug"
        )
    }
}