package com.example.newtest.app.feature.news.news


import com.example.newtest.data.model.NewsItem
import moxy.MvpView

interface INewsView : MvpView {
    fun onNewsSuccess(news : List<NewsItem>)
    fun onNewsError()
}