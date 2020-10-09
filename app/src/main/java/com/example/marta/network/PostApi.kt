package com.example.marta.network


import com.example.marta.model.*
import retrofit2.Response
import retrofit2.http.*


interface PostApi {


    @POST("oauth/token")
    suspend fun getToken(
//        @Header("dev") test: String,
//        @Header("Authorization")  basicToken:String,
        @Query("username")  id: String,
        @Query("password")  password: String,
        @Query("grant_type")  grant_type: String
    ) : Response<LoginResponce>

    @POST("registration/code")
//    suspend fun getPosts(@Field ("phone")phone:String) : Response<Any>
    suspend fun getPosts(@Body verRequest: VerRequest) : Response<ModelResponce>

    @POST("registration/sign_up")
    suspend fun getSigUp(@Body sigUpRequest: SigUpRequest):Response<SigUpResponce>



}