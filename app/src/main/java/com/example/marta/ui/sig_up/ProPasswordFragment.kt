package com.example.marta.ui.sig_up

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.marta.R
import com.example.marta.pin2.PinFragment
import com.example.marta.pin2.PinPasFragment
import com.example.marta.utils.PreferencesUtil
import kotlinx.android.synthetic.main.fragment_password.*

class ProPasswordFragment :Fragment(R.layout.fragment_password){
    private lateinit var etPhone: EditText
    private var hasshh: String? = null
    lateinit var preferencesUtil: PreferencesUtil
    init {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           val bundle=arguments
        val password=arguments?.get("pass")
        preferencesUtil = PreferencesUtil(getDefaultSharedPreferences(context) )
//        if (!preferencesUtil.isLogin()){
//            preferencesUtil.clearHash("")
//        }

        next_btn.setOnClickListener {
            if (password==et_conf_pass.text.toString())
            {
                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.enter_left,
                    R.anim.exit_left,
                    R.anim.enter_right,
                    R.anim.exit_right
                )?.addToBackStack(null)?.replace(
                    R.id.container2,

                   PinPasFragment()
                )?.commit()
            }
            else {
                et_conf_code.error = getString(R.string.err_msg_otp_length)
            }
        }

    }


}