package com.example.newtest.app.di.module


import com.example.newtest.data.datasourse.rest.INetService
import com.example.newtest.data.datasourse.rest.provider.INetProvider
import com.example.newtest.data.datasourse.rest.provider.NetProviderImpl
import com.example.newtest.data.repository.INewsRepository
import com.example.newtest.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationDaggerModule {

    @Provides
    @Singleton
    fun provideNetProvider(): INetProvider {
        return NetProviderImpl()
    }

    @Provides
    @Singleton
    fun provideNetService(netProvider: INetProvider): INetService {
        return netProvider.netService
    }

    @Provides
    @Singleton
    fun provideNewsRepository(netService: INetService): INewsRepository {
        return  NewsRepositoryImpl(netService)
    }
}
