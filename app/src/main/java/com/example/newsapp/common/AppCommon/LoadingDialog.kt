package com.example.newsapp.common.AppCommon

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.newsapp.R

class LoadingDialog(val activity: Activity) {
    private lateinit var dialog: AlertDialog

    fun startLoading() {
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_progress_dialog, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()
    }

    fun dismiss() {
        dialog.dismiss()
    }
}