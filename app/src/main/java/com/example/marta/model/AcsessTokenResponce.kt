package com.example.marta.model


import com.google.gson.annotations.SerializedName

data class AcsessTokenResponce(
    @SerializedName("aud")
    val aud: List<String>? = listOf(),
    @SerializedName("authorities")
    val authorities: List<String>? = listOf(),
    @SerializedName("client_id")
    val clientId: String? = "",
    @SerializedName("clientVersion")
    val clientVersion: Any? = Any(),
    @SerializedName("exp")
    val exp: Int? = 0,
    @SerializedName("lastVisit")
    val lastVisit: Long? = 0,
    @SerializedName("phone")
    val phone: String? = "",
    @SerializedName("pswdExp")
    val pswdExp: Boolean? = false,
    @SerializedName("redirectUri")
    val redirectUri: String? = "",
    @SerializedName("scope")
    val scope: List<String>? = listOf(),
    @SerializedName("user_name")
    val userName: String? = ""
)