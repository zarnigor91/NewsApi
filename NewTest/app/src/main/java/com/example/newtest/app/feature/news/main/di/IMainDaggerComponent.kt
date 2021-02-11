package com.example.newtest.app.feature.news.main.di

import com.example.newtest.app.di.component.IApplicationDaggerComponent
import com.example.newtest.app.feature.news.main.MainActivity
import com.example.newtest.data.repository.IRepositoryProvider
import dagger.Component

@MainActivityScope
@Component(
    dependencies = [IApplicationDaggerComponent::class],
    modules = [MainDaggerModule::class,
               MainDaggerModules::class
              ]
)
interface IMainDaggerComponent : IRepositoryProvider{

    fun inject(mainActivity: MainActivity)


    @Component.Factory
    interface Factory {
        fun create(component: IApplicationDaggerComponent): IMainDaggerComponent
    }

    companion object {
        fun create(component: IMainDaggerComponent): IMainDaggerComponent =
            DaggerIMainDaggerComponent
                .factory()
                .create(component)
    }

}