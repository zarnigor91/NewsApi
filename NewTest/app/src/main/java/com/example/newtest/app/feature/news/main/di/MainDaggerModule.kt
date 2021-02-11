package com.example.newtest.app.feature.news.main.di

import androidx.fragment.app.FragmentFactory
import com.example.newtest.app.di.fragment.ProviderFragmentFactory
import dagger.Binds
import dagger.Module

@Module(includes = [MainDaggerModule.Binder::class])
object MainDaggerModule {

    @Module
    interface Binder {

        @Binds
        @MainActivityScope
        fun bindProviderFragmentFactory(
            factory: ProviderFragmentFactory
        ): FragmentFactory
    }

}