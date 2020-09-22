package com.example.marta




import android.os.Bundle
import android.view.View
import android.widget.EditText

import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_otp.*


class ConfirmFragment : Fragment(R.layout.fragment_otp){
      private  lateinit var pwdOtpEt:EditText
      private lateinit var pwdOtpTil:TextInputLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle=Bundle()
        val sigUpFragment =SigUpFragment()

        pwdOtpEt=view.findViewById(R.id.otp_password) as EditText
        pwdOtpTil=view.findViewById(R.id.layout_otp_password) as TextInputLayout

        request_sms_btn.setVisibility(View.VISIBLE)
        progress_bar.setVisibility(View.GONE)
            countdown_tv.setVisibility(View.GONE)
//        otp_password.setText(getArguments()?.getString("OTP"))


        next_btn.setOnClickListener {
            if (validate(pwdOtpEt, pwdOtpTil)) {
               bundle.putString("conf",pwdOtpEt.text.toString())
                sigUpFragment.arguments=bundle
                supportfragmentmanager()?.beginTransaction()?.addToBackStack(null)?.replace(
                    R.id.container2,
                    SigUpFragment()
                )?.commit()
            }
        }

        }
    private fun validate(et: EditText, til: TextInputLayout): Boolean {
        val value = et.text.toString().trim { it <= ' ' }
        if (value.isEmpty() || value.length < 6) {
            til.error = getString(R.string.err_msg_otp_length)
            et.requestFocus()
            return false
        } else {
            til.isErrorEnabled = false
        }
        return true
    }






    }

