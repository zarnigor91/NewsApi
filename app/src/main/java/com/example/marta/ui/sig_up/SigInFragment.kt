package com.example.marta.ui.sig_up

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.model.LoginRequest
import com.example.marta.model.LoginResponce
import com.example.marta.model.SigUpRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.SigUpViewModel
import com.example.marta.vm.loginViewModel
import com.example.marta.vm.sipUpViewModel
import kotlinx.android.synthetic.main.fragment_password.*

import javax.inject.Inject


class SigInFragment : Fragment(R.layout.fragment_password) {
    @Inject
    lateinit var api: PostApi
    lateinit var signViewModel:SigUpViewModel

    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val paswordFragment: Fragment = ProPasswordFragment()
        signViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SigUpViewModel::class.java
        )
        injectDependency(this)

        var mBundle: Bundle? = Bundle()
        mBundle = arguments
//        mBundle?.getString("conf")
        val conf = arguments?.getString("conf")




//        viewModel.init(api,preferencesUtil)
        next_btn.setOnClickListener {
            Log.d("hashh", "hash" + preferencesUtil.getHash())
            signViewModel.loadSigUp(
                SigUpRequest(
                    preferencesUtil.getHash(),
                    conf!!,
                    et_conf_pass.text.toString()
                )
            )
            loadData()

            signViewModel.getToken(LoginRequest("fayzullaeva@mayasoft.uz", "B7TsAyxD","password"))
            loadLogin()
            mBundle?.putString("pass", et_conf_pass.text.toString())
            paswordFragment.arguments = mBundle
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            )?.addToBackStack(null)?.replace(
                R.id.container2,
                paswordFragment
            )?.commit()
        }

        signViewModel.postLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show()
        })

    }

    private fun injectDependency(fragment: SigInFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }

    private fun loadData() {
        signViewModel.init(api,preferencesUtil)
        signViewModel.postLiveData.observe(viewLifecycleOwner, postObserver)
    }

    private val postObserver = Observer<String> {
        preferencesUtil.getHash()
    }

    private fun loadLogin(){
        signViewModel.init(api, preferencesUtil)
        signViewModel.tokenLiveData.observe(viewLifecycleOwner, postObserve)
    }

    private  val postObserve= Observer<LoginResponce>{
        preferencesUtil.setLogin(true)
    }

}