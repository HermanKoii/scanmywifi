package com.wifiscanner.utils

/**
 * Utility class for interpreting WiFi signal strength levels
 * Provides consistent and comprehensive signal strength analysis
 */
object SignalStrengthInterpreter {
    /**
     * Interprets signal strength in dBm with standardized categories
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
     * Offers visual representation of network quality
     * @param signalStrength Signal strength in dBm (negative value)
     * @return Resource ID for color representation
     */
    fun getSignalStrengthColorRes(signalStrength: Int): Int {
        return when {
            signalStrength >= -50 -> android.R.color.holo_green_light
            signalStrength >= -60 -> android.R.color.holo_green_dark
            signalStrength >= -70 -> android.R.color.holo_orange_light
            signalStrength >= -80 -> android.R.color.holo_red_light
            else -> android.R.color.holo_red_dark
        }
    }
}