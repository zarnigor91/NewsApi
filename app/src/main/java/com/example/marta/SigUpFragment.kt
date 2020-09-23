package com.example.marta

import android.R.attr.key
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marta.app.App
import com.example.marta.model.SigUpRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.SigUpViewModel
import com.example.marta.vm.sipUpViewModel
import kotlinx.android.synthetic.main.fragment_password.*

import javax.inject.Inject


class SigUpFragment :Fragment(R.layout.fragment_password){
    @Inject
    lateinit var api: PostApi

    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency(this)
        loadData()
        var mBundle: Bundle? = Bundle()
        mBundle = arguments
//        mBundle?.getString("conf")
        val conf = arguments?.getString("conf")

        val  viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SigUpViewModel::class.java)

        viewModel.init(api)
        next_btn.setOnClickListener {
            Log.d("hashh","hash"+preferencesUtil.getTokenn())
            sipUpViewModel().loadSigUp(SigUpRequest(preferencesUtil.getTokenn(), conf!!,et_conf_pass.text.toString()))
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
                R.id.container2,
                SigUpFragment()
            )?.commit()
        }

        sipUpViewModel().postLiveData.observe(viewLifecycleOwner, Observer{
             Toast.makeText(context,"sucess", Toast.LENGTH_SHORT).show()
        })

    }

    private fun injectDependency(fragment:SigUpFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }

    private fun loadData(){

        sipUpViewModel().init(api)

        sipUpViewModel().postLiveData.observe(viewLifecycleOwner, postObserver)
    }
    private val postObserver = Observer<String> {
        preferencesUtil.getTokenn()
    }

}