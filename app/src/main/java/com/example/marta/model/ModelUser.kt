package com.example.marta.model


import com.google.gson.annotations.SerializedName

data class ModelUser(
    @SerializedName("access_token")
    val accessToken: String? = "",
    @SerializedName("authorities")
    val authorities: List<String>? = listOf(),
    @SerializedName("clientVersion")
    val clientVersion: Any? = Any(),
    @SerializedName("expires_in")
    val expiresIn: Int? = 0,
    @SerializedName("lastVisit")
    val lastVisit: Long? = 0,
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("pswdExp")
    val pswdExp: Boolean? = false,
    @SerializedName("redirectUri")
    val redirectUri: String? = "",
    @SerializedName("scope")
    val scope: String? = "",
    @SerializedName("token_type")
    val tokenType: String? = ""
)