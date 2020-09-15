package com.example.marta

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.marta.model.LoginRequest
import com.example.marta.model.VerRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import javax.inject.Inject
import com.example.marta.vm.loginViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment :Fragment(R.layout.tel_number_layout){
    @Inject
    lateinit var api: PostApi
    @Inject
    lateinit var preferencesUtil: PreferencesUtil


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency(this)
        loadData()
        bt_naxt_login.setOnClickListener {
//            loginViewModel().getToken(LoginRequest(et_email.text.toString(), et_password.text.toString(),et_grant_type.text.toString()))
            loginViewModel().getToken(VerRequest("998998343007"))
        }
    }

    private fun injectDependency(fragment:LoginFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }

    private fun loadData(){

        loginViewModel().init(api, preferencesUtil)

        loginViewModel().tokenLiveData.observe(viewLifecycleOwner, postObserver)
    }

    private val postObserver = Observer<String> {
//        if(it==""){
//            Toast.makeText(context,"sucsess",Toast.LENGTH_SHORT).show()
//            Log.d("Sucsess","sucses")
//        }
//        else{
//            Log.d("Error","er")
//            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
//        }
        preferencesUtil.setToken(it)//Token yozildi
    }
}