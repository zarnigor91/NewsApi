package com.example.newtest.app.di2.module.app

import android.content.Context
import com.example.newtest.app.Application
import com.example.newtest.app.di2.scope.AppScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ViewModelModule::class,
        RepoModule::class,
        ProviderModule::class
    ]
)
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}