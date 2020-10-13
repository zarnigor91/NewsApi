package com.example.marta.utils

import android.content.SharedPreferences

class PreferencesUtil(
    private val preferences: SharedPreferences
) {

    fun getHash(): String {
        return preferences.getString("hash", "") ?: ""
    }

    fun setHash(hash: String) {
        preferences.edit().putString("hash", hash).apply()
    }

    fun clearHash(hash: String) {
        preferences.edit().putString("hash", hash).apply()
    }

    fun getAcsessToken(): String {
        return preferences.getString("acsesToken", "") ?: ""
    }

    fun setAcsessToken(token: String) {
        return preferences.edit().putString("acsesToken", token).apply()
    }

    fun getRefreshToken(): String {
        return preferences.getString("token", "") ?: ""
    }

    fun setRefreshToken(token: String) {
        return preferences.edit().putString("token", token).apply()
    }

    fun isLogin(): Boolean {
        return preferences.getBoolean("isLogin", false)
    }

    fun setLogin(isLogin: Boolean) {
        return preferences.edit().putBoolean("isLogin", isLogin).apply()
    }
}

