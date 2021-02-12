package com.example.newtest.data.repository

import com.example.newtest.data.model.NewsItem
import io.reactivex.Single

interface INewsRepository {
    fun getNews() : Single<List<NewsItem>>
}