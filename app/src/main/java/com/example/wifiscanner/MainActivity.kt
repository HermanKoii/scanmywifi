package com.example.wifiscanner

import android.Manifest
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var btnScanNetworks: MaterialButton
    private lateinit var rvWifiNetworks: RecyclerView
    private lateinit var tvNoNetworks: TextView
    private lateinit var wifiManager: WifiManager

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        btnScanNetworks = findViewById(R.id.btn_scan_networks)
        rvWifiNetworks = findViewById(R.id.rv_wifi_networks)
        tvNoNetworks = findViewById(R.id.tv_no_networks)
        
        // Initialize WiFi Manager
        wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager

        // Setup RecyclerView
        rvWifiNetworks.layoutManager = LinearLayoutManager(this)
        // TODO: Set up WiFi network adapter when implemented

        // Set up scan button click listener
        btnScanNetworks.setOnClickListener {
            checkAndScanNetworks()
        }
    }

    private fun checkAndScanNetworks() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
        )

        val permissionsNeeded = permissions.filter { 
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED 
        }

        if (permissionsNeeded.isEmpty()) {
            scanNetworks()
        } else {
            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), PERMISSIONS_REQUEST_CODE)
        }
    }

    private fun scanNetworks() {
        if (!wifiManager.isWifiEnabled) {
            Snackbar.make(btnScanNetworks, "WiFi is disabled. Enabling...", Snackbar.LENGTH_SHORT).show()
            wifiManager.isWifiEnabled = true
        }

        // TODO: Implement actual WiFi scanning logic
        updateNetworksList()
    }

    private fun updateNetworksList() {
        // TODO: Implement actual network list population
        tvNoNetworks.visibility = View.VISIBLE
        rvWifiNetworks.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                scanNetworks()
            } else {
                Snackbar.make(btnScanNetworks, "Permissions required for WiFi scanning", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}