package com.example.newtest.app.feature.news.main.di

import com.example.newtest.app.feature.news.news.di.NewsDaggerModule
import dagger.Module

@Module(
    includes = [
        NewsDaggerModule::class
    ]
)

object MainDaggerModules
