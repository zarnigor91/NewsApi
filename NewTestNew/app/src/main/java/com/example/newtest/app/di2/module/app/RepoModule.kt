package com.example.newtest.app.di2.module.app

import com.example.newtest.app.di2.scope.AppScope
import com.example.newtest.data.datasourse.rest.INetService
import com.example.newtest.data.repository.INewsRepository
import com.example.newtest.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepoModule {
    @Provides
    @AppScope
    fun provideNewsRepository(service: INetService): INewsRepository {
        return NewsRepositoryImpl(service)
    }
}