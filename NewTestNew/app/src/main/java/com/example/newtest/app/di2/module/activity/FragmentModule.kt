package com.example.newtest.app.di2.module.activity

import com.example.newtest.app.di2.scope.FragmentScope
import com.example.newtest.app.feature.news.news.NewsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeNewsFragment():NewsFragment
}