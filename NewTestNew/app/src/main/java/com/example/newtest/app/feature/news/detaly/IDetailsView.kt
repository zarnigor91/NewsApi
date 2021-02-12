//package com.example.newtest.app.feature.news.detaly
//
//import com.example.newtest.data.model.NewsItem
//import moxy.MvpView
//import moxy.viewstate.strategy.AddToEndSingleStrategy
//import moxy.viewstate.strategy.StateStrategyType
//
//interface IDetailsView : MvpView{
//
//    @StateStrategyType(AddToEndSingleStrategy::class)
//    fun onDetailsSuccess(news :NewsItem)
//
//    @StateStrategyType(AddToEndSingleStrategy::class)
//    fun onDetailsError()
//}