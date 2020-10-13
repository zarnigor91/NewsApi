package com.example.marta.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("grant_type")
    val grantType:String,
    @SerializedName("refresh_token")
    val refreshToken:String
)