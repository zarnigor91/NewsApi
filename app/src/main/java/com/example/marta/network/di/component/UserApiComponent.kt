package com.example.marta.network.di.component




import com.example.marta.app.App
import com.example.marta.ui.login.LoginFragment
import com.example.marta.SigUpFragment
import com.example.marta.network.di.module.UserApiModule
import com.example.marta.network.di.scope.AppScope
import dagger.Component


@AppScope
@Component(modules = [UserApiModule::class])
interface UserApiComponent {
    fun inject(app: App)
    fun inject(fragment: LoginFragment)
      fun inject(fragment: SigUpFragment)
}