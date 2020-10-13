package com.example.marta.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.pin2.PinPasFragment
import com.example.marta.ui.language.LanguageFragment
import com.example.marta.ui.login.LoginActivity
import com.example.marta.ui.login.LoginFragment
import com.example.marta.utils.PreferencesUtil
import javax.inject.Inject

class FirstFragment :Fragment(){
    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency(this )
        if (preferencesUtil.getHash().isNotEmpty()){
                   val intent = Intent(context, LoginActivity::class.java)
       intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
       startActivity(intent)

        }
        else{
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            )?.addToBackStack(null)?.replace(
                R.id.container2,
                LanguageFragment()

            )?.commit()
        }
    }
    private fun injectDependency(fragment: FirstFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }
}