package uz.mayasoft.marta.wallet.network


import uz.mayasoft.marta.wallet.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import uz.mayasoft.marta.wallet.model.*


interface PostApi {


    @POST("oauth/token")
    suspend fun getToken(
        @Query("username") id: String,
        @Query("password") password: String,
        @Query("grant_type") grant_type: String
    ): Response<LoginResponce>

    @POST("registration/code")
    suspend fun getPosts(@Body verRequest: VerRequest): Response<ModelResponce>

    @POST("registration/sign_up")
    suspend fun getSigUp(@Body sigUpRequest: SigUpRequest): Response<SigUpResponce>

    @POST("oauth/token")
    suspend fun getAcsess(
        @Query("grant_type") grant_type: String,
        @Query("refresh_token") refresh_token: String
    ): Response<LoginResponce>

    @POST("oauth/check_token")
    suspend fun chekAcsess(@Query("token") token:String):Response<AcsessTokenResponce>


}