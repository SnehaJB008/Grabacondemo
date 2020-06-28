package com.demo.grabacondemo.utils

import android.content.Context
import android.content.SharedPreferences




class PreferenceUtils {
    private fun putBooleanInPreferences(
        context: Context,
        value: Boolean, key: String
    ): Boolean {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
        return true
    }
    fun putStringInPreferences(
        context: Context,
        value: String?, key: String?
    ): Boolean {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
        return true
    }

    private fun getBooleanFromPreferences(
        context: Context,
        defaultValue: Boolean, key: String
    ): Boolean {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun getStringFromPreferences(
        context: Context,
        defaultValue: String?, key: String?
    ): String? {
        val sharedPreferences = context
            .getSharedPreferences(
                Constants().PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        return sharedPreferences.getString(key, defaultValue)
    }
    fun isUserLogin(context: Context): Boolean {
        return getBooleanFromPreferences(
            context, false,
            Constants().IS_USER_LOGIN
        )
    }

    fun setUserLogin(
        context: Context,
        isLogin: Boolean
    ) {
        putBooleanInPreferences(
            context, isLogin,
            Constants().IS_USER_LOGIN
        )
    }

    fun setUserDataAvailable(
        context: Context,
        isData: Boolean
    ) {
        putBooleanInPreferences(
            context, isData,
            Constants().IS_USER_DATA
        )
    }

    fun isUserDataAvailable(context: Context): Boolean {
        return getBooleanFromPreferences(
            context, false,
            Constants().IS_USER_DATA
        )
    }
    //retrieve email address
    fun getEmail(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_EMAIL
        )
    }

    //save email address
    fun setEmail(
        context: Context,
        email: String
    ) {
        putStringInPreferences(context, email, Constants().USER_EMAIL)
    }

    //retrieve PhoneNumber
    fun getNumber(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_NUMBER
        )
    }

    //save email address
    fun setNumber(
        context: Context,
        number: String
    ) {
        putStringInPreferences(context, number, Constants().USER_NUMBER)
    }

    //retrieve name
    fun getName(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_NAME
        )
    }

    //save name
    fun setName(
        context: Context,
        name: String
    ) {
        putStringInPreferences(context, name, Constants().USER_NAME)
    }
    //retrieve age
    fun getAge(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_AGE
        )
    }

    //save age
    fun setAge(
        context: Context,
        age: String
    ) {
        putStringInPreferences(context, age, Constants().USER_AGE)
    }

    //retrieve location
    fun getLocation(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_LOCATION
        )
    }

    //save location
    fun setLocation(
        context: Context,
        location: String
    ) {
        putStringInPreferences(context, location, Constants().USER_LOCATION)
    }


    //retrieve gender
    fun getGender(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_GENDER
        )
    }

    //save gender
    fun setGender(
        context: Context,
        gender: String
    ) {
        putStringInPreferences(context, gender, Constants().USER_GENDER)
    }

    //retrieve profission
    fun getProfission(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().USER_PROFESSION

        )
    }

    //save profission
    fun setProfission(
        context: Context,
        profission: String
    ) {
        putStringInPreferences(context, profission, Constants().USER_PROFESSION)
    }
    //retrieve Image
    fun getProfileImage(context: Context): String? {
        return getStringFromPreferences(
            context, "",
            Constants().PROFILE_IMAGE
        )
    }

    //save pass
    fun setProfileImage(
        context: Context,
        imageuri: String
    ) {
        putStringInPreferences(context, imageuri, Constants().PROFILE_IMAGE)
    }

    fun clearealldata( context: Context){
        val preferences: SharedPreferences =
            context.getSharedPreferences( Constants().PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }
}