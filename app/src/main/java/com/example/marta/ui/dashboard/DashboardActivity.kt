package com.example.marta.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.dialog.AlertDialogs
import com.example.marta.model.AcsessTokenRequest
import com.example.marta.model.AcsessTokenResponce
import com.example.marta.model.LoginResponce
import com.example.marta.model.RefreshTokenRequest
import com.example.marta.network.PostApi
import com.example.marta.pin2.PinPasFragment
import com.example.marta.ui.language.LanguageActivity
import com.example.marta.ui.login.LoginFragment
import com.example.marta.ui.splash.SplashActivity
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.LoginViewModel
import com.example.marta.vm.TokenViewModel
import javax.inject.Inject


class DashboardActivity :AppCompatActivity(){

    private var acsess: AcsessTokenResponce?=null
    private var acsessToken: LoginResponce?=null
    @Inject
    lateinit var tokenPreferencesUtil: PreferencesUtil

    @Inject
    lateinit var api: PostApi
     lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val fragment = DashboardFragment()
        if(savedInstanceState==null){
            addFragment(fragment)
        }
        AlertDialogs.closeProgress()
    }



    override fun onRestart() {
        super.onRestart()

    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.enter_left,
            R.anim.exit_left,
            R.anim.enter_right,
            R.anim.exit_right
        ).addToBackStack(null)
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }



}