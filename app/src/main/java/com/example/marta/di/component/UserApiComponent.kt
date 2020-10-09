package com.example.marta.di.component


import com.example.marta.app.App
import com.example.marta.di.module.UserApiModule
import com.example.marta.di.scope.AppScope
import com.example.marta.ui.FirstFragment
import com.example.marta.ui.login.LoginFragment
import com.example.marta.ui.sig_up.ConfirmFragment
import com.example.marta.ui.sig_up.SigUpFragment
import com.example.marta.ui.sig_up.SignUpFragment
import dagger.Component


@AppScope
@Component(modules = [UserApiModule::class])
interface UserApiComponent {
    fun inject(app: App)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SigUpFragment)
    fun inject(fragment: ConfirmFragment)
    fun inject(fragment: LoginFragment)
    fun  inject(fragment: FirstFragment)
}