package com.example.newtest.data.datasource.network

import com.example.newtest.data.datasource.network.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET

interface NetService {
    @GET("topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF")
    fun  fetchNews(): Call<NewsModel>
}