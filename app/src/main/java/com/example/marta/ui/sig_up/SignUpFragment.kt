package com.example.marta.ui.sig_up

import android.content.DialogInterface
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
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.dialog.AlertDialogs
import com.example.marta.model.VerRequest
import com.example.marta.network.PostApi
import com.example.marta.ui.language.LanguageFragment
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.signUpViewModel
import com.hbb20.CountryCodePicker
import io.michaelrocks.libphonenumber.android.NumberParseException
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil
import io.michaelrocks.libphonenumber.android.Phonenumber
import kotlinx.android.synthetic.main.tel_number_layout.*
import javax.inject.Inject


class SignUpFragment : Fragment(R.layout.tel_number_layout),
    DialogInterface.OnDismissListener {
    @Inject
    lateinit var api: PostApi

    @Inject
    lateinit var preferencesUtil: PreferencesUtil
    private lateinit var countryCodePicker: CountryCodePicker
    private lateinit var etPhone: EditText
    private var hasshh: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency(this)
        loadData()
        tv_tel_number.text=requireContext().getString(R.string.phone_number)
             if (!preferencesUtil.isLogin()){
                 preferencesUtil.clearHash("")
             }
        bt_naxt_login.setOnClickListener {
            et_num.text = ccp.fullNumber
            signUpViewModel().getHash(VerRequest(et_num.text.toString()))
//            AlertDialogs.progressDialog(requireContext(),requireContext().getString(R.string.wait))
//            AlertDialogs.showMessage(requireContext(),"dsffds")
        }
        assignViews()
        addTextWatcher()
        registerCarrierEditText()
        imb_back_phone_fragment.setOnClickListener {
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

    private fun injectDependency(fragment: SignUpFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }

    private fun loadData() {
        signUpViewModel().init(api, preferencesUtil)
        signUpViewModel().hashLiveData.observe(viewLifecycleOwner, Observer {
            if (it!=null) {

                hasshh = it.hash
                preferencesUtil.clearHash(it.hash)
                 AlertDialogs.closeProgress()
                val oneFragment: Fragment = ConfirmFragment()
                val works = arrayOf(hasshh, et_num.text.toString())
                val args = Bundle()
                args.putStringArray("works", works)
                oneFragment.arguments = args
                Log.d("tokk", "tok" + it)
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


        })
        signUpViewModel().errorLiveData.observe(viewLifecycleOwner, Observer {
            if (it==409){
                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.enter_left,
                    R.anim.exit_left,
                    R.anim.enter_right,
                    R.anim.exit_right
                )?.addToBackStack(null)?.replace(
                    R.id.container2,
                    ProPasswordFragment()
                )?.commit()
            }
        })

//        Log.d("tokk","tok"+ loginViewModel().tokenLiveData.observe(viewLifecycleOwner, postObserver))
    }

    private val postObserver = Observer<String> {
        if (!it.isNullOrEmpty()) {
            hasshh = it
            preferencesUtil.clearHash(it)
        } else {
            Log.d("Errorr", "er")
            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun assignViews() {
        etPhone = requireView().findViewById(R.id.et_phone)
        countryCodePicker = requireView().findViewById(R.id.ccp)
        countryCodePicker.registerCarrierNumberEditText(etPhone)
    }

    private fun addTextWatcher() {
        etPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                et_num.text = "Load $s to CCP."
            }

            override fun afterTextChanged(s: Editable) {
                if (isvalidatsia()) {
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

    private fun isvalidatsia(): Boolean {
        var isValid = false
        val swissNumberStr = ccp.selectedCountryCodeWithPlus + et_phone.text.toString()
        Log.d("var", "var22 " + swissNumberStr)
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.createInstance(context)
        try {
            val swissNumberProto: Phonenumber.PhoneNumber = phoneUtil.parse(
                swissNumberStr,
                countryCodePicker.selectedCountryNameCode
            )
            isValid = phoneUtil.isValidNumber(swissNumberProto)
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: " + e.toString())
        }
        return isValid
    }


    override fun onDismiss(p0: DialogInterface?) {
        p0?.dismiss()
    }
}
