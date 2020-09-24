package com.example.marta.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marta.R
import com.example.marta.ui.SettingPassFragment
import kotlinx.android.synthetic.main.fragment_password.*

class ProPasswordFragment :Fragment(R.layout.fragment_password){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           val bundle=arguments
        val password=arguments?.get("pass")
        if (password==et_conf_pass.text.toString())
        {
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            )?.addToBackStack(null)?.replace(
                R.id.container2,
           SettingPassFragment()
            )?.commit()
        }
        else {
            et_conf_code.error = getString(R.string.err_msg_otp_length)
        }
    }

}