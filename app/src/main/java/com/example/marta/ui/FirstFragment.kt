package com.example.marta.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marta.R
import com.example.marta.ui.language.LanguageFragment
import com.example.marta.ui.login.LoginFragment
import com.example.marta.utils.PreferencesUtil
import javax.inject.Inject

class FirstFragment :Fragment(){
    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (preferencesUtil.getHash().isNotEmpty()){
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
        else{
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            )?.addToBackStack(null)?.replace(
                R.id.container2,
               LoginFragment()
            )?.commit()
        }
    }
}