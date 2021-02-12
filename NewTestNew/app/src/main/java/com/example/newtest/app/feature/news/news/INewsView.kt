package com.example.newtest.app.feature.news.news


import com.example.newtest.data.model.NewsItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface INewsView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onNewsSuccess(news : List<NewsItem>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onNewsError()
}