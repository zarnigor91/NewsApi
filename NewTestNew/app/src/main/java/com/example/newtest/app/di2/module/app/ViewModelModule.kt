package com.example.newtest.app.di2.module.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newtest.app.di2.scope.GithubViewModelFactory
import com.example.newtest.app.di2.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
//    @Binds
//    fun bindViewModelFactory(factory:GithubViewModelFactory):ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(Vm::class)
//    fun someViewModel(v:Vm): ViewModel
}