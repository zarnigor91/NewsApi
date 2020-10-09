package com.example.marta.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.model.LoginRequest
import com.example.marta.network.PostApi
import com.example.marta.ui.sig_up.SignUpFragment
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.loginViewModel
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject

class LoginFragment :Fragment(R.layout.login_fragment){
    @Inject
    lateinit var api: PostApi
    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        loginViewModel().init(api, preferencesUtil)

        injectDependency(this)
        loadLogin()
 
        bt_next_login.setOnClickListener {
            loginViewModel().getToken(LoginRequest("fayzullaeva@mayasoft.uz", "B7TsAyxD","password"))
//            Log.d("phone",""+et_num.text.toString())

            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            )?.addToBackStack(null)?.replace(
                R.id.container2,
               SignUpFragment()
            )?.commit()

        }
//
//        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
//            LoginViewModel::class.java
//        )
        }
    private fun injectDependency(fragment: LoginFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }
   private fun loadLogin(){
        loginViewModel().init(api, preferencesUtil)
        loginViewModel().tokenLiveData.observe(viewLifecycleOwner, postObserve)
    }

     private  val postObserve= Observer<String>{
         if(!it.isNullOrEmpty()){
             preferencesUtil.setToken(it)
             Log.d("tokenn","tok"+it)
         }
         else{
             Log.d("Errorr","er")
             Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
         }
     }

    }



