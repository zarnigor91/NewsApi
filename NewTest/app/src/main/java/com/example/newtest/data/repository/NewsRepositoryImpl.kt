package com.example.newtest.data.repository

import com.example.newtest.data.datasourse.rest.INetService
import com.example.newtest.data.model.NewsItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepositoryImpl(
    private val netService: INetService
) : INewsRepository {
    override fun getNews(): Single<List<NewsItem>> {
        return netService.fetchNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}