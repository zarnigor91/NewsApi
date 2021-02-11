package com.example.newtest.app.feature.news.news


import com.example.newtest.data.repository.INewsRepository
import moxy.MvpPresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private val newsRepository: INewsRepository
) : MvpPresenter<INewsView>() {
    fun getNews() {
        newsRepository.getNews()
            .subscribe(
                { viewState.onNewsSuccess(it) },
                { viewState.onNewsError() }
            )
    }
}