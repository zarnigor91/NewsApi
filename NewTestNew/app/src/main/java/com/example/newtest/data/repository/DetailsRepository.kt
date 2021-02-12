package com.example.newtest.data.repository

import com.example.newtest.data.model.NewsItem

interface DetailsRepository {
    fun getDetail(newsItem: NewsItem)
}