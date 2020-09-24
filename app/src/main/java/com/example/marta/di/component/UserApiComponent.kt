package com.example.marta.di.component




import com.example.marta.ui.login.ConfirmFragment
import com.example.marta.app.App
import com.example.marta.ui.login.LoginFragment
import com.example.marta.ui.login.SigUpFragment
import com.example.marta.di.module.UserApiModule
import com.example.marta.di.scope.AppScope
import dagger.Component


@AppScope
@Component(modules = [UserApiModule::class])
interface UserApiComponent {
    fun inject(app: App)
    fun inject(fragment: LoginFragment)
      fun inject(fragment: SigUpFragment)
    fun inject(fragment: ConfirmFragment)
}