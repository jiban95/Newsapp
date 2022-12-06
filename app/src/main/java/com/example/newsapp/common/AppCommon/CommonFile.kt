package com.example.newsapp.common.AppCommon

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class CommonFile {
    companion object CommonMethod {
        fun setProgressDialog(context: Context, message: String): ProgressDialog {
            val mProgressDialog = ProgressDialog(context)
            mProgressDialog.setTitle("Loading")
            mProgressDialog.setMessage("This is MESSAGE")
            mProgressDialog.show()
            return mProgressDialog
        }

        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val n = cm.activeNetwork
                if (n != null) {
                    val nc = cm.getNetworkCapabilities(n)
                    //It will check for both wifi and cellular network
                    return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }
                return false
            } else {
                val netInfo = cm.activeNetworkInfo
                return netInfo != null && netInfo.isConnectedOrConnecting
            }
        }

    }

}