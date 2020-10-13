package com.example.marta.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.model.AcsessTokenRequest
import com.example.marta.model.AcsessTokenResponce
import com.example.marta.model.LoginResponce
import com.example.marta.model.RefreshTokenRequest
import com.example.marta.network.PostApi
import com.example.marta.pin2.PinPasFragment
import com.example.marta.ui.language.LanguageActivity
import com.example.marta.ui.splash.SplashActivity
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.TokenViewModel
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
                val loginFragment=LoginFragment()

                val intent = Intent(this, LanguageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)


            }
        }
    }
}