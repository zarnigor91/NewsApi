package com.example.newtest.app.di2.module.app

import com.example.newtest.app.di2.scope.AppScope
import com.example.newtest.app.feature.news.news.NewsPresenter
import com.example.newtest.data.repository.INewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ProviderModule {

    @Provides
    @AppScope
    fun provideNewsRepository(repo:INewsRepository):NewsPresenter{
        return NewsPresenter(repo)
    }
}