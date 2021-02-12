package com.example.newtest.app.di2.component

import com.example.newtest.app.Application
import com.example.newtest.app.di2.module.app.ActivitiesModule
import com.example.newtest.app.di2.module.app.AppModule
import com.example.newtest.app.di2.module.app.NetModule
import com.example.newtest.app.di2.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class,
        NetModule::class
    ]
)
interface AppComponent : AndroidInjector<Application> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<Application>
}