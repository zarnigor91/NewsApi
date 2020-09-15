package com.example.marta.network


import com.example.marta.model.LoginRequest
import com.example.marta.model.ModelResponce
import com.example.marta.model.ModelUser
import com.example.marta.model.VerRequest
import retrofit2.Response
import retrofit2.http.*


interface PostApi {


//    @POST("oauth/token")
//    suspend fun getPosts(
////        @Header("dev") test: String,
////        @Header("Authorization")  basicToken:String,
//        @Query("username")  id: String,
//        @Query("password")  password: String,
//        @Query("grant_type")  grant_type: String
//    ) : Response<ModelUser>

    @POST("registration/code")
//    suspend fun getPosts(@Field ("phone")phone:String) : Response<Any>
    suspend fun getPosts(@Body verRequest: VerRequest) : Response<ModelResponce>


}