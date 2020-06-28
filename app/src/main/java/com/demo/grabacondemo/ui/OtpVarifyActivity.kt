package com.demo.grabacondemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.demo.grabacondemo.R
import com.demo.grabacondemo.utils.Constants
import com.demo.grabacondemo.utils.PreferenceUtils
import com.demo.grabacondemo.utils.Utils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_varify.*
import java.util.concurrent.TimeUnit

class OtpVarifyActivity : AppCompatActivity() {
    val TAG = OtpVarifyActivity::class.simpleName
    var phoneNumber :String  = ""
    var storedVerificationId  :String = ""
    var mResendToken : PhoneAuthProvider.ForceResendingToken ?= null
    var mAuth :FirebaseAuth ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_varify)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mAuth = FirebaseAuth.getInstance()
        phoneNumber = intent?.getStringExtra(Constants().INTENT_DATA)?:""
        init()
    }

    private fun init() {
        tvDetail.text = resources.getString(R.string.verification_code_data,phoneNumber)
        btnVerify.setOnClickListener {
            val code = edtCode.text.toString()
            if(code.isNullOrEmpty() || code.length < 6){
                edtCode.setError("Ender Code")
                return@setOnClickListener
            }
            progressBar.visibility = View.VISIBLE
            verifyCode(code)
        }
        sendVerificationCode()
    }

    private fun sendVerificationCode() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            callbacks)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        finish()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            progressBar.visibility = View.VISIBLE
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Utils().showToast(this@OtpVarifyActivity,e.getLocalizedMessage())
            Utils().showToast(this@OtpVarifyActivity,"enter valid number")
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            storedVerificationId = verificationId
            mResendToken = token
        }
    }

    fun verifyCode (code :String){
        var credential : PhoneAuthCredential= PhoneAuthProvider.getCredential(storedVerificationId,code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this) { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    edtCode.setText(credential.smsCode)
                    PreferenceUtils().setUserLogin(this,true)
                    val intent = Intent(this,UserDetailActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.e(TAG,task.exception.toString())
                    }
                }
            }?.addOnFailureListener(this){
                Utils().showToast(this,it.getLocalizedMessage())
            }
    }
}