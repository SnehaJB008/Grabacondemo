package com.demo.grabacondemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.grabacondemo.R
import com.demo.grabacondemo.utils.Constants
import com.demo.grabacondemo.utils.PreferenceUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        init()
    }

    private fun init() {
        btnLogin.setOnClickListener {
            verifyPhone()
        }
    }

    private fun verifyPhone() {
        val phoneNumber = "+91" + edtNumber.text.toString()
        if(phoneNumber.isNullOrEmpty() || (phoneNumber.length < 10)){
            edtNumber.setError("Enter valid number")
            return
        }
        PreferenceUtils().setNumber(this,phoneNumber)
        val intent = Intent(this,OtpVarifyActivity::class.java)
        intent.putExtra(Constants().INTENT_DATA,phoneNumber)
        startActivity(intent)
    }
}