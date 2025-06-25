package com.wifiscanner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wifiscanner.R
import com.wifiscanner.model.WiFiNetwork

/**
 * Adapter for displaying WiFi networks with security type in a RecyclerView.
 *
 * @property networks List of WiFi networks to display
 */
class WiFiNetworkAdapter(
    private val networks: List<WiFiNetwork>
) : RecyclerView.Adapter<WiFiNetworkAdapter.WiFiNetworkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WiFiNetworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wifi_network, parent, false)
        return WiFiNetworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: WiFiNetworkViewHolder, position: Int) {
        val network = networks[position]
        holder.bind(network)
    }

    override fun getItemCount() = networks.size

    /**
     * ViewHolder for WiFi network items with security type display.
     */
    inner class WiFiNetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ssidTextView: TextView = itemView.findViewById(R.id.network_ssid)
        private val signalStrengthTextView: TextView = itemView.findViewById(R.id.network_signal_strength)
        private val securityTypeTextView: TextView = itemView.findViewById(R.id.network_security_type)

        /**
         * Bind WiFi network data to the view.
         *
         * @param network WiFi network to display
         */
        fun bind(network: WiFiNetwork) {
            ssidTextView.text = network.ssid
            signalStrengthTextView.text = "${network.signalStrength} dBm"
            
            // Set security type text and color
            securityTypeTextView.text = network.securityType.name
            securityTypeTextView.setTextColor(
                ContextCompat.getColor(
                    itemView.context, 
                    getSecurityTypeColor(network.securityType)
                )
            )
        }

        /**
         * Get color resource based on security type.
         *
         * @param securityType Security type of the network
         * @return Color resource ID
         */
        private fun getSecurityTypeColor(securityType: WiFiNetwork.SecurityType): Int {
            return when (securityType) {
                WiFiNetwork.SecurityType.OPEN -> R.color.security_open
                WiFiNetwork.SecurityType.WEP -> R.color.security_weak
                WiFiNetwork.SecurityType.WPA -> R.color.security_medium
                WiFiNetwork.SecurityType.WPA2 -> R.color.security_strong
                WiFiNetwork.SecurityType.WPA3 -> R.color.security_very_strong
                WiFiNetwork.SecurityType.UNKNOWN -> R.color.security_unknown
            }
        }
    }
}