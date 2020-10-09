package com.example.marta.utils

import android.content.SharedPreferences

class PreferencesUtil(
    private val preferences: SharedPreferences
){

    fun getHash(): String {
    return preferences.getString("hash","")?:""
}

fun setHash(hash: String) {
    preferences.edit().putString("hash", hash).apply()
}

    fun clearHash(hash: String) {
        preferences.edit().putString("hash",hash).apply()
    }

    fun getToken():String{
        return preferences.getString("token","")?:""
    }

    fun setToken(token:String){
        return preferences.edit().putString("token",token).apply()
    }

}

