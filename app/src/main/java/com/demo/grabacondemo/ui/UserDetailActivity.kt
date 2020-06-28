package com.demo.grabacondemo.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.demo.grabacondemo.R
import com.demo.grabacondemo.utils.PreferenceUtils
import com.demo.grabacondemo.utils.Utils
import com.demo.grabacondemo.utils.ValidationUtils
import kotlinx.android.synthetic.main.activity_user_detail.*


class UserDetailActivity : AppCompatActivity() {
    var gender = arrayOf("Female", "Male", "Other")
    var genderId : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun init() {
        setSpinner()
        btnSave.setOnClickListener {
            validateDetails()
        }
    }

    private fun validateDetails() {
        val validationUtils = ValidationUtils()
        val preferenceUtils = PreferenceUtils()
        if(!validationUtils.isValidEmail(edtEmailAddress)||!validationUtils.isValidString(edtPersonName,"name")
            ||!validationUtils.isValidString(edtLocation,"location")||!validationUtils.isValidString(edtAge,"age")||!validationUtils.isValidString(edtProfession,"profession")){
            return
        }
        preferenceUtils.setUserDataAvailable(this,true)
        preferenceUtils.setEmail(this,edtEmailAddress.text.toString())
        preferenceUtils.setName(this,edtPersonName.text.toString())
        preferenceUtils.setGender(this,genderId)
        preferenceUtils.setAge(this,edtAge.text.toString())
        preferenceUtils.setLocation(this,edtLocation.text.toString())
        preferenceUtils.setProfission(this,edtProfession.text.toString())

        Utils().launchStartActivity(this,MainActivity::class.java)
    }

    private fun setSpinner() {
        spGender.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?, arg1: View?,
                position: Int, id: Long
            ) {
                genderId = gender[position] //saving the value selected
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
            }
        })

        val spin_adapter = ArrayAdapter(
            this@UserDetailActivity,
            android.R.layout.simple_spinner_dropdown_item,
            gender
        )
        spGender.setAdapter(spin_adapter)
    }
}