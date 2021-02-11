package com.example.newtest.app.di.module


import com.example.newtest.data.datasourse.rest.NetService
import com.example.newtest.data.datasourse.rest.provider.NetProvider
import com.example.newtest.data.datasourse.rest.provider.NetProviderImpl
import com.example.newtest.data.repository.NewsRepository
import com.example.newtest.data.repository.NewsRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext



@Module
class MainModule {

    @Provides
    @Singleton
    fun provideNetProvider(): NetProvider {
        return NetProviderImpl()
    }

    @Provides
    @Singleton
    fun provideNetService(netProvider: NetProvider): NetService {
        return netProvider.netService
    }

    @Provides
    @Singleton
    fun provideNewsRepository(netService: NetService): NewsRepository {
        return  NewsRepositoryImpl(netService)
    }
}
