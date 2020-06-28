package com.demo.grabacondemo

import android.content.Context
import androidx.multidex.MultiDexApplication

class Application  :MultiDexApplication(){

    private var instance: Application? = null
    private var mContext: Context? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
        mContext = this
    }
}