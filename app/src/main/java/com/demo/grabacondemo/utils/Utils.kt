package com.demo.grabacondemo.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class Utils {

    fun launchStartActivity(activity: Activity, c: Class<*>?) {
        val intent = Intent(activity, c)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK
        )
        activity.startActivity(intent)
    }

    fun checkInternetConnection(context: Context): Boolean {
        val conMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo.isAvailable
                && conMgr.activeNetworkInfo.isConnected)
    }

    fun showToast(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}