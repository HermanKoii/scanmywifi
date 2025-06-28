package com.wifiscanner.utils

/**
 * Utility class for interpreting WiFi signal strength levels
 * Provides comprehensive signal strength interpretation and color mapping
 */
object SignalStrengthInterpreter {
    // Mock color resource class for testing
    object R {
        object color {
            const val holo_green_light = 1
            const val holo_green_dark = 2
            const val holo_orange_light = 3
            const val holo_red_light = 4
            const val holo_red_dark = 5
        }
    }

    /**
     * Interprets signal strength in dBm and provides a human-readable description
     * @param signalStrength Signal strength in dBm (negative value)
     * @return A descriptive text representing the signal strength quality
     */
    fun interpretSignalStrength(signalStrength: Int): String {
        return when {
            signalStrength >= -50 -> "Excellent (Strong)"
            signalStrength >= -60 -> "Good (Very Good)"
            signalStrength >= -70 -> "Fair (Moderate)"
            signalStrength >= -80 -> "Weak (Poor)"
            else -> "Very Weak (Unreliable)"
        }
    }

    /**
     * Provides a color resource based on signal strength
     * @param signalStrength Signal strength in dBm (negative value)
     * @return Resource ID for color representation
     */
    fun getSignalStrengthColorRes(signalStrength: Int): Int {
        return when {
            signalStrength >= -50 -> R.color.holo_green_light
            signalStrength >= -60 -> R.color.holo_green_dark
            signalStrength >= -70 -> R.color.holo_orange_light
            signalStrength >= -80 -> R.color.holo_red_light
            else -> R.color.holo_red_dark
        }
    }

    /**
     * Provides a signal strength icon based on the network's signal strength
     * @param signalStrength Signal strength in dBm (negative value)
     * @return Resource ID for the appropriate WiFi signal icon
     */
    fun getSignalStrengthIconRes(signalStrength: Int): Int {
        return when {
            signalStrength >= -50 -> com.example.wifiscanner.R.drawable.ic_wifi_signal_high
            signalStrength >= -60 -> com.example.wifiscanner.R.drawable.ic_wifi_signal_high
            signalStrength >= -70 -> com.example.wifiscanner.R.drawable.ic_wifi_signal_medium
            signalStrength >= -80 -> com.example.wifiscanner.R.drawable.ic_wifi_signal_low
            else -> com.example.wifiscanner.R.drawable.ic_wifi_signal_none
        }
    }
}