package com.wifiscanner.utils

object SignalStrengthInterpreter {
    /**
     * Interprets signal strength in dBm and provides a human-readable description
     * @param signalStrength Signal strength in dBm (negative value)
     * @return A descriptive text representing the signal strength quality
     */
    fun interpretSignalStrength(signalStrength: Int): String {
        return when {
            signalStrength >= -50 -> \"Excellent (Strong)\"
            signalStrength >= -60 -> \"Good (Very Good)\"
            signalStrength >= -70 -> \"Fair (Moderate)\"
            signalStrength >= -80 -> \"Weak (Poor)\"
            else -> \"Very Weak (Unreliable)\"
        }
    }

    /**
     * Provides a color resource based on signal strength
     * @param signalStrength Signal strength in dBm (negative value)
     * @return Resource ID for color representation
     */
    fun getSignalStrengthColorRes(signalStrength: Int): Int {
        // Use predefined color codes for testing
        return when {
            signalStrength >= -50 -> 1 // Green light
            signalStrength >= -60 -> 2 // Dark green
            signalStrength >= -70 -> 3 // Orange
            signalStrength >= -80 -> 4 // Red light
            else -> 5 // Dark red
        }
    }
}