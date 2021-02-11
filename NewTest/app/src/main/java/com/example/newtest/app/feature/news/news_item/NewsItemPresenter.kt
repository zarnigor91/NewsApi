package com.example.newtest.app.feature.news.news_item

import com.arellomobile.mvp.MvpPresenter
import com.example.newtest.app.feature.news.NewsView
import com.example.newtest.data.repository.NewsRepository
import javax.inject.Inject

class NewsItemPresenter @Inject constructor(
    private val newsRepository: NewsRepository
) : MvpPresenter<NewsView>() {
    fun getNews() {
        newsRepository.getNews()
            .subscribe(
                { viewState.onNewsSuccess(it) },
                { viewState.onNewsError() }
            )
    }
}