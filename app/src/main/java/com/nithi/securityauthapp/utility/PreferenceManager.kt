package com.nithi.securityauthapp.utility

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager @Inject constructor(private val preferences: SharedPreferences) {
    companion object {
        const val USER_DETAIL = "user"
        const val USER_DETAIL_IV = "user_iv"
        const val NAME = "name"
        const val ENCRYPTED = "encrypted"
    }
    fun clearAll() {
        val editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

    private fun setPreferenceStringData(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveName(value: String){
        setPreferenceStringData(NAME,value)
    }

    fun encryptedData(value: String){
        setPreferenceStringData(ENCRYPTED,value)
    }
    fun saveUserName(value:String){
        setPreferenceStringData(USER_DETAIL,value)
    }

    fun setIv(value: String){
        setPreferenceStringData(USER_DETAIL_IV,value)
    }
    fun getUserName() =preferences.getString(USER_DETAIL,"")
    fun getName()=preferences.getString(NAME,"")
    fun getEncrypte() =preferences.getString(ENCRYPTED,"")
    fun getIv() =preferences.getString(USER_DETAIL_IV,"")

}