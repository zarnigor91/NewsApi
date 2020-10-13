package uz.mayasoft.marta.wallet.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.app.App
import uz.mayasoft.marta.wallet.model.AcsessTokenRequest
import uz.mayasoft.marta.wallet.model.AcsessTokenResponce
import uz.mayasoft.marta.wallet.model.LoginResponce
import uz.mayasoft.marta.wallet.model.RefreshTokenRequest
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.pin2.PinPasFragment
import uz.mayasoft.marta.wallet.ui.language.LanguageActivity
import uz.mayasoft.marta.wallet.ui.splash.SplashActivity
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import uz.mayasoft.marta.wallet.vm.TokenViewModel
import javax.inject.Inject

class LoginActivity :AppCompatActivity(){
    private var acsess: AcsessTokenResponce?=null
    private var acsessToken: LoginResponce?=null
    @Inject
    lateinit var tokenPreferencesUtil: PreferencesUtil

    @Inject
    lateinit var api: PostApi
    lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        injectDependency(this)
        tokenViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            TokenViewModel::class.java
        )
        loadToken()
    }

    private fun loadToken(){

        tokenViewModel.init(api)
        tokenViewModel.acsessToken.observe(this, androidx.lifecycle.Observer {
            acsess=it
            check()
        })
        tokenViewModel.chekToken(AcsessTokenRequest(tokenPreferencesUtil.getAcsessToken()))

    }
    private fun injectDependency(activity: SplashActivity) {
        (this.application as App).getApiComponent().inject(activity)
    }
    private fun loadRefreshToken(){
        tokenViewModel.init(api)
        tokenViewModel.getAcsessToken(RefreshTokenRequest(" refresh_token",tokenPreferencesUtil.getRefreshToken()))
        tokenViewModel.refreshToken.observe(this,androidx.lifecycle.Observer{
//            Log.d("login",""+it.accessToken)
            acsessToken=it
        })
    }    private fun injectDependency(activity: LoginActivity) {
        (this.application as App).getApiComponent().inject(activity)
    }
    private fun check(){

        if (acsess!=null){
            supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            ).addToBackStack(null).replace(
                R.id.container_login,
                PinPasFragment()
            ).commit()
        }
        else
        {
            val pisFragment= PinPasFragment()
            loadRefreshToken()
            tokenViewModel.getAcsessToken(RefreshTokenRequest("refresh_token",tokenPreferencesUtil.getRefreshToken()))
            if (acsessToken!=null)
            {
                tokenPreferencesUtil.setAcsessToken(acsessToken!!.accessToken.toString())
//                addFragment(pisFragment)
                supportFragmentManager.beginTransaction().setCustomAnimations(
                    R.anim.enter_left,
                    R.anim.exit_left,
                    R.anim.enter_right,
                    R.anim.exit_right
                ).addToBackStack(null).replace(
                    R.id.container_login,
                    PinPasFragment()
                ).commit()





//                val intent = Intent(this, DashboardActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)
            }
            else{
                val loginFragment= LoginFragment()

                val intent = Intent(this, LanguageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)


            }
        }
    }
}