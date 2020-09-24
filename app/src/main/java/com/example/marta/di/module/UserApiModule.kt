package com.example.marta.di.module

import android.content.Context
import com.example.marta.network.PostApi
import com.example.marta.di.scope.AppScope
import com.example.marta.utils.PreferencesUtil
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class UserApiModule(private val context: Context) {

    private val AUTH_HEADER_EXCEPTED_LIST = arrayOf(
        "signup",
        "verify"
    )

    @Provides
    @AppScope
    fun provideUserApiService(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://auth.marta.uz/")/*BuildConfig.API_URL*/
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    var client = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context))
        .build()

    @Provides
    @AppScope
    fun provideRetrofitBuilder(preferencesUtil: PreferencesUtil): OkHttpClient {
        val authInterceptor = Interceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()

            val url = original.url.toString()
            if (AUTH_HEADER_EXCEPTED_LIST.none { url == it }) {

            }
            val request = requestBuilder
//                .header("Authorization", Credentials.basic("dev", "test"))
                .build()
            chain.proceed(request)
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor)

                .build()
        val gson = GsonBuilder()
            .create()
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .addInterceptor(ChuckInterceptor(context))
            .build()
    }

    @AppScope
    @Provides
    fun providesPreferences(): PreferencesUtil {
        val sharedPreferences = context.getSharedPreferences("OauthPrefs", Context.MODE_PRIVATE)
        return PreferencesUtil(sharedPreferences)
    }

    @Provides
    @AppScope
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }
}
