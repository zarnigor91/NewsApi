package com.example.marta.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.marta.app.App
import com.example.marta.R
import com.example.marta.model.VerRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.loginViewModel
import com.hbb20.CountryCodePicker
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import kotlinx.android.synthetic.main.login_fragment.bt_naxt_login
import kotlinx.android.synthetic.main.tel_number_layout.*
import javax.inject.Inject


class LoginFragment :Fragment(R.layout.tel_number_layout){
    @Inject
    lateinit var api: PostApi
    @Inject
    lateinit var preferencesUtil: PreferencesUtil
    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var etPhone: EditText
    private var hasshh:String?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val oneFragment: Fragment = ConfirmFragment()
        injectDependency(this)
        loadData()

        bt_naxt_login.setOnClickListener {
            et_num.text = ccp.fullNumber
            val works = arrayOf(hasshh,et_num.text.toString())
            val args = Bundle()
            args.putStringArray("works",works)
            oneFragment.arguments = args
//            loginViewModel().getToken(LoginRequest(et_email.text.toString(), et_password.text.toString(),et_grant_type.text.toString()))

            loginViewModel().getHash(VerRequest(et_num.text.toString()))
            Log.d("phone",""+et_num.text.toString())
            activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.enter_left,
                R.anim.exit_left,
                R.anim.enter_right,
                R.anim.exit_right
            )?.addToBackStack(null)?.replace(
                R.id.container2,
                oneFragment
            )?.commit()

        }
        assignViews()
        addTextWatcher()
        registerCarrierEditText()

    }

    private fun injectDependency(fragment: LoginFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }

    private fun loadData(){
        loginViewModel().init(api, preferencesUtil)
        loginViewModel().tokenLiveData.observe(viewLifecycleOwner, postObserver)
//        Log.d("tokk","tok"+ loginViewModel().tokenLiveData.observe(viewLifecycleOwner, postObserver))
    }

    private val postObserver = Observer<String> {
        if(!it.isNullOrEmpty()){
            hasshh=it

            preferencesUtil.clearToken(it)
            Log.d("tokk","tok"+it)
        }
        else{
            Log.d("Errorr","er")
            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
        }

    }

    private fun assignViews() {
        etPhone = requireView().findViewById(R.id.et_phone)
        countryCodePicker = requireView().findViewById(R.id.ccp)
        countryCodePicker.registerCarrierNumberEditText(etPhone)
    }

    private fun addTextWatcher() {
        etPhone.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                et_num.text = "Load $s to CCP."
            }

            override fun afterTextChanged(s: Editable) {
                if (isvalidatsia()){
                    val left: Int = et_phone.text.length
                    val filterArray: Array<InputFilter?> = arrayOfNulls<InputFilter>(1)
                        filterArray[0] = LengthFilter(left)
                       etPhone.setFilters(filterArray)
                }
            }
        })
    }
    private fun registerCarrierEditText() {
        ccp.registerCarrierNumberEditText(etPhone)
    }

    private fun isvalidatsia():Boolean{
       var isValid = false
        val swissNumberStr = ccp.selectedCountryCodeWithPlus + et_phone.text.toString()
        Log.d("var","var22 "+swissNumberStr)
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.createInstance(context)
        try {
            val swissNumberProto: Phonenumber.PhoneNumber = phoneUtil.parse(swissNumberStr,countryCodePicker.selectedCountryNameCode )
              isValid = phoneUtil.isValidNumber(swissNumberProto)
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: " + e.toString())
        }
      return isValid
    }

}

