package com.example.newtest.app.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val mApplication: Application) {
    @Provides
    fun provideContext(): Context {
        return mApplication
    }

//    @Provides
//    fun provideApplication(): Application {
//        return mApplication
//    }
}
