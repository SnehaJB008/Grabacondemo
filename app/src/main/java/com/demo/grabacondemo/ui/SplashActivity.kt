package com.demo.grabacondemo.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.demo.grabacondemo.R
import com.demo.grabacondemo.utils.Constants
import com.demo.grabacondemo.utils.PreferenceUtils
import com.demo.grabacondemo.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        GlobalScope.launch {
//            startKoin(applicationContext, listOf(appModule))
            delay(Constants().SPLASH_SCREEN_TIME_OUT.toLong())
            if(PreferenceUtils().isUserLogin(this@SplashActivity)){
                if(PreferenceUtils().isUserDataAvailable(this@SplashActivity)){
                    Utils().launchStartActivity(this@SplashActivity, MainActivity::class.java)
                }else{
                    Utils().launchStartActivity(this@SplashActivity, UserDetailActivity::class.java)
                }
            }else{
                Utils().launchStartActivity(this@SplashActivity, LoginActivity::class.java)
            }
        }
    }
}