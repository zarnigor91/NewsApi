package uz.mayasoft.marta.wallet.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.app.App
import uz.mayasoft.marta.wallet.model.LoginRequest
import uz.mayasoft.marta.wallet.model.LoginResponce
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.pin2.PinPasFragment
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import uz.mayasoft.marta.wallet.vm.loginViewModel
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

 
        bt_next_login.setOnClickListener {
            loginViewModel().getToken(LoginRequest("fayzullaeva@mayasoft.uz", "B7TsAyxD","password"))
//            Log.d("phone",""+et_num.text.toString())
            loadLogin()
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

     private  val postObserve= Observer<LoginResponce>{
             preferencesUtil.setLogin(true)
             Log.d("tokenn","tok"+it)
             Log.d("Errorr","er")
     }

    }



