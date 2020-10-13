package com.example.marta.di.component


import com.example.marta.app.App
import com.example.marta.di.module.UserApiModule
import com.example.marta.di.scope.AppScope
import com.example.marta.ui.FirstFragment
import com.example.marta.ui.login.LoginActivity
import com.example.marta.ui.login.LoginFragment
import com.example.marta.ui.sig_up.ConfirmFragment
import com.example.marta.ui.sig_up.SigInFragment
import com.example.marta.ui.sig_up.SignUpFragment
import com.example.marta.ui.splash.SplashActivity
import dagger.Component


@AppScope
@Component(modules = [UserApiModule::class])
interface UserApiComponent {
    fun inject(app: App)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SigInFragment)
    fun inject(fragment: ConfirmFragment)
    fun inject(fragment: LoginFragment)
    fun  inject(fragment: FirstFragment)
    fun inject(activity: SplashActivity)
    fun inject(activity: LoginActivity)
}