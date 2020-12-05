package com.dungle.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class NetworkUtil {
    companion object {
        // Check internet connection
        fun isNetworkAvailable(context: Context?): Boolean {
            if (context != null) {
                val cm: ConnectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                if (wifiNetwork != null && wifiNetwork.isConnected) {
                    return true
                }
                val mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                if (mobileNetwork != null && mobileNetwork.isConnected) {
                    return true
                }
                val activeNetwork = cm.activeNetworkInfo
                if (activeNetwork != null && activeNetwork.isConnected) {
                    return true
                }
            }
            return false
        }
    }
}