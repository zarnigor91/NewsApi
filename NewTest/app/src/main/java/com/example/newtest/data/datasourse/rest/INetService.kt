package com.example.newtest.data.datasourse.rest

import com.example.newtest.data.model.NewsItem
import com.example.newtest.data.model.NewsModel
import io.reactivex.Single
import retrofit2.http.GET

interface INetService {
    @GET("topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF")
    fun  fetchNews(): Single<List<NewsItem>>
}