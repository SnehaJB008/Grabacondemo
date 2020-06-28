package com.demo.grabacondemo.utils

import android.util.Log
import android.widget.EditText
import java.util.regex.Pattern

class ValidationUtils {
    private val TAG = ValidationUtils::class.java.simpleName
    private val EmailRegex = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private val EMAIL = "Email"
    private val PASSWORD_ERROR_TEXT = "Password must be between 6 to 30 characters."
    private val EMAIL_ERROR_TEXT = "Please enter a valid email address."
    private val PASSWORD_MAX_LENGTH = 30
    private val PASSWORD_MIN_LENGTH = 6
    private val ERROR_TEXT = " is required."
    private val PASSWORD = "Password"

    fun isValidEmail(editText: EditText): Boolean {
        return try {
            if (isValidObject<EditText>(editText)) {
                if (isValidString(editText, EMAIL)) {
                    val pattern =
                        Pattern.compile(EmailRegex)

                    /*       if (android.util.Patterns.EMAIL_ADDRESS.matcher(getString(editText).trim())
                                                    .matches()) {
                                                return true;
                                            }*/if (pattern.matcher(
                            getString(
                                editText
                            )?:"".trim({ it <= ' ' })
                        )
                            .matches()
                    ) {
                        true
                    } else {
                        editText.requestFocus()
                        editText.error = EMAIL_ERROR_TEXT
                        false
                    }
                } else {
                    editText.requestFocus()
                    editText.error =EMAIL + ERROR_TEXT
                    false
                }
            } else {
                Log.d(TAG, "Edit text is null.")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            false
        }
    }
    fun isValidPassword(editText: EditText): Boolean {
        return try {
            if (isValidObject<EditText>(editText)) {
                if (isValidString(editText, PASSWORD)) {
                    editText.error = null
                    if (getString(editText)!!.length in PASSWORD_MIN_LENGTH..PASSWORD_MAX_LENGTH
                    ) {
                        true
                    } else {
                        editText.requestFocus()
                        editText.error = PASSWORD_ERROR_TEXT
                        false
                    }
                } else {
                    Log.d(TAG, "isValidPassword hint" + editText.hint)
                    editText.error = PASSWORD + ERROR_TEXT
                    false
                }
            } else {
                Log.d(TAG, "Edit text is null")
                false
            }
        } catch (e: java.lang.Exception) {
            Log.e(TAG, e.message, e)
            false
        }
    }
    fun isValidString(editText: EditText?, hint: String): Boolean {
        return try {
            hint.replace('*', ' ')
            if (editText != null) if (isValidString(
                    getString(
                        editText
                    )
                )
            ) {
                editText.error = null
                true
            } else {
                editText.requestFocus()
                editText.error = hint + ERROR_TEXT
                false
            } else {
                Log.d(TAG, "Edit text is null")
                false
            }
        } catch (e: java.lang.Exception) {
            Log.e(TAG, e.message, e)
            false
        }
    }

    fun getString(editText: EditText?): String? {
        return try {
            if (editText != null) {
                if (isValidString(
                        editText.text.toString()
                    )
                ) editText.text.toString() else ""
            } else {
                Log.d(TAG, "Edit text is null")
                ""
            }
        } catch (e: java.lang.Exception) {
            Log.e(TAG, e.message, e)
            ""
        }
    }
    fun isValidString(string: String?): Boolean {
        return try {
            if (string != null && string.length > 0 && !string.isEmpty()) true else false
        } catch (e: java.lang.Exception) {
            Log.e(TAG, e.message, e)
            false
        }
    }
    fun <T> isValidObject(`object`: T?): Boolean {
        return try {
            if (`object` != null) true else false
        } catch (e: java.lang.Exception) {
            Log.e(TAG, e.message, e)
            false
        }
    }
}