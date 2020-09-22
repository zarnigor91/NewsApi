package com.example.marta.utils

import android.content.SharedPreferences

class PreferencesUtil(
    private val preferences: SharedPreferences
){

    fun getTokenn(): String {
    return preferences.getString("token","")?:""
}

fun setToken(token: String) {
    preferences.edit().putString("token", token).apply()
}

    fun clearToken(token: String) {
        preferences.edit().putString("token",token).apply()
    }
}

