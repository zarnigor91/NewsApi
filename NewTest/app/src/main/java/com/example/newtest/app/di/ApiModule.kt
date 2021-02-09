package com.example.newtest.app.di

import com.example.newtest.BuildConfig
import com.example.newtest.data.datasource.network.utils.Constants.INSTANCE.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
object ApiModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }

        okHttpBuilder.addInterceptor({
            val request = it.request()
            val url = request.url().newBuilder()
                .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                .build()
            it.proceed(request.newBuilder().url(url).build())
        })
        return Retrofit.Builder()
            .baseUrl(BASEec_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())
            .build()
}

}