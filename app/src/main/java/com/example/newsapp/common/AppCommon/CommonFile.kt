package com.example.newsapp.common.AppCommon

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class CommonFile {
    companion object CommonMethod {
        fun setProgressDialog(context: Context, message: String): ProgressDialog {
            val mProgressDialog = ProgressDialog(context)
            mProgressDialog.setTitle("Loading")
            mProgressDialog.setMessage(message)
            mProgressDialog.show()
            mProgressDialog.setCancelable(false)
            return mProgressDialog
        }

        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val n = cm.activeNetwork
                if (n != null) {
                    val nc = cm.getNetworkCapabilities(n)
                    //It will check for both wifi and cellular network
                    return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                }
                return false
            } else {
                val netInfo = cm.activeNetworkInfo
                return netInfo != null && netInfo.isConnectedOrConnecting
            }
        }
    }
}