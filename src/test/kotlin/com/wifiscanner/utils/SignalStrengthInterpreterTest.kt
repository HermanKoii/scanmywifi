package com.wifiscanner.utils

import org.junit.Test
import org.junit.Assert.*
import com.example.wifiscanner.R

class SignalStrengthInterpreterTest {

    @Test
    fun `test signal strength interpretation`() {
        assertEquals("Excellent (Strong)", SignalStrengthInterpreter.interpretSignalStrength(-40))
        assertEquals("Good (Very Good)", SignalStrengthInterpreter.interpretSignalStrength(-55))
        assertEquals("Fair (Moderate)", SignalStrengthInterpreter.interpretSignalStrength(-65))
        assertEquals("Weak (Poor)", SignalStrengthInterpreter.interpretSignalStrength(-75))
        assertEquals("Very Weak (Unreliable)", SignalStrengthInterpreter.interpretSignalStrength(-90))
    }

    @Test
    fun `test signal strength color resources`() {
        assertEquals(android.R.color.holo_green_light, SignalStrengthInterpreter.getSignalStrengthColorRes(-40))
        assertEquals(android.R.color.holo_green_dark, SignalStrengthInterpreter.getSignalStrengthColorRes(-55))
        assertEquals(android.R.color.holo_orange_light, SignalStrengthInterpreter.getSignalStrengthColorRes(-65))
        assertEquals(android.R.color.holo_red_light, SignalStrengthInterpreter.getSignalStrengthColorRes(-75))
        assertEquals(android.R.color.holo_red_dark, SignalStrengthInterpreter.getSignalStrengthColorRes(-90))
    }

    @Test
    fun `test signal strength icon resources`() {
        assertEquals(R.drawable.ic_wifi_signal_high, SignalStrengthInterpreter.getSignalStrengthIconRes(-40))
        assertEquals(R.drawable.ic_wifi_signal_high, SignalStrengthInterpreter.getSignalStrengthIconRes(-55))
        assertEquals(R.drawable.ic_wifi_signal_medium, SignalStrengthInterpreter.getSignalStrengthIconRes(-65))
        assertEquals(R.drawable.ic_wifi_signal_low, SignalStrengthInterpreter.getSignalStrengthIconRes(-75))
        assertEquals(R.drawable.ic_wifi_signal_none, SignalStrengthInterpreter.getSignalStrengthIconRes(-90))
    }
}