package com.example.newtest.app.di.module

import com.example.newtest.data.repository.INewsRepository
import com.example.newtest.data.repository.IRepositoryProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationDaggerModuleRepository.Provider::class])
object ApplicationDaggerModuleRepository {

    @Module
    object Provider {

        @JvmStatic
        @Provides
        @Singleton
        fun articleRepository(
            repositoryProvider: IRepositoryProvider
        ): INewsRepository = repositoryProvider.newsRepository

    }
}