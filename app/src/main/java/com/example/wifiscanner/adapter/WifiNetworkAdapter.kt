package com.example.wifiscanner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wifiscanner.R
import com.example.wifiscanner.model.WifiNetwork
import com.wifiscanner.utils.SignalStrengthInterpreter

class WifiNetworkAdapter(
    private val networks: List<WifiNetwork>
) : RecyclerView.Adapter<WifiNetworkAdapter.WifiNetworkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiNetworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wifi_network_item, parent, false)
        return WifiNetworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: WifiNetworkViewHolder, position: Int) {
        val network = networks[position]
        holder.bind(network)
    }

    override fun getItemCount() = networks.size

    inner class WifiNetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val networkNameTextView: TextView = itemView.findViewById(R.id.networkNameTextView)
        private val networkDetailsTextView: TextView = itemView.findViewById(R.id.networkDetailsTextView)
        private val signalStrengthImageView: ImageView = itemView.findViewById(R.id.signalStrengthImageView)
        private val signalStrengthTextView: TextView = itemView.findViewById(R.id.signalStrengthTextView)

        fun bind(network: WifiNetwork) {
            networkNameTextView.text = network.ssid
            networkDetailsTextView.text = "Security: ${network.securityType} | BSSID: ${network.bssid}"
            signalStrengthImageView.setImageResource(network.getSignalStrengthIcon())
            signalStrengthTextView.apply {
                text = network.getSignalStrengthText()
                setTextColor(context.getColor(SignalStrengthInterpreter.getSignalStrengthColorRes(network.signalStrength)))
            }
        }
    }
}