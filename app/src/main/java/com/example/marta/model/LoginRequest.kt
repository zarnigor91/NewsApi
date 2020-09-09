package com.example.marta.model

data class LoginRequest(
    val username:String,
    val password:String,
    val grant_type:String
)