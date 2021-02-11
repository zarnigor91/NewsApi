package com.example.newtest.app.feature.news

import com.arellomobile.mvp.MvpView
import com.example.newtest.data.model.NewsItem

interface NewsView : MvpView {
    fun onNewsSuccess(news : List<NewsItem>)
    fun onNewsError()
}