package uz.mayasoft.marta.wallet.di.component


import uz.mayasoft.marta.wallet.app.App
import uz.mayasoft.marta.wallet.di.module.UserApiModule
import uz.mayasoft.marta.wallet.di.scope.AppScope
import uz.mayasoft.marta.wallet.ui.FirstFragment
import uz.mayasoft.marta.wallet.ui.login.LoginActivity
import uz.mayasoft.marta.wallet.ui.login.LoginFragment
import uz.mayasoft.marta.wallet.ui.sig_up.ConfirmFragment
import uz.mayasoft.marta.wallet.ui.sig_up.SigInFragment
import uz.mayasoft.marta.wallet.ui.sig_up.SignUpFragment
import uz.mayasoft.marta.wallet.ui.splash.SplashActivity
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