package com.example.newtest.app.di.component


import com.example.newtest.app.di.module.MainModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {

}
