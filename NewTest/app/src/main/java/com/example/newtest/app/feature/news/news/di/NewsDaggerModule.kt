package com.example.newtest.app.feature.news.news.di

import androidx.fragment.app.Fragment
import com.example.newtest.app.di.fragment.FragmentKey
import com.example.newtest.app.feature.news.news.NewsFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [NewsDaggerModule.Binder::class])

object NewsDaggerModule {

    @Module
    interface Binder {
        @Binds
        @IntoMap
        @FragmentKey(NewsFragment::class)
        fun newsFragment(newsFragment: NewsFragment) : Fragment
    }
}