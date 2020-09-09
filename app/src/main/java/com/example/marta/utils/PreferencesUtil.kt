package com.example.marta.utils

import android.content.SharedPreferences

class PreferencesUtil(
    private val preferences: SharedPreferences
){

    fun getToken(): String {
    return preferences.getString("token","")?:""
}

fun setToken(token: String) {
    preferences.edit().putString("token", token).apply()
}
}

