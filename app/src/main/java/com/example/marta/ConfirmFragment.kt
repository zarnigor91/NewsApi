package com.example.marta


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_otp.*
import java.text.SimpleDateFormat
import java.util.*


class ConfirmFragment : Fragment(R.layout.fragment_otp){
      private  lateinit var pwdOtpEt:EditText
      private lateinit var pwdOtpTil:TextInputLayout
    private var timer: CountDownTimer? = null
    var isRunning: Boolean = false
    var time_in_milli_seconds = 0L
    private val timeFormat by lazy(LazyThreadSafetyMode.NONE) {
        SimpleDateFormat("ss", Locale.getDefault())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle=Bundle()
        val sigUpFragment =SigUpFragment()
        startTimer(60000)
        pwdOtpEt=view.findViewById(R.id.otp_password) as EditText
        pwdOtpTil=view.findViewById(R.id.layout_otp_password) as TextInputLayout

        request_sms_btn.setOnClickListener {
            startTimer(60000)
        }

        next_btn.setOnClickListener {
            if (validate(pwdOtpEt, pwdOtpTil)) {
               bundle.putString("conf",pwdOtpEt.text.toString())
                sigUpFragment.arguments=bundle
                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
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
//    private fun startTimer(time_in_seconds: Long) {
//        timer = object : CountDownTimer((time_in_seconds*1000), 1000) {
//            override fun onTick(p0: Long) {
//                request_sms_btn.visibility = View.GONE
//                progress_bar.visibility = View.VISIBLE
//                countdown_tv.visibility = View.VISIBLE
//                countdown_tv.text = timeFormat.format(time_in_milli_seconds)
//                Log.d("secund",""+timeFormat.format(time_in_milli_seconds))
//            }
//
//            override fun onFinish() {
//                request_sms_btn.visibility = View.VISIBLE
//                progress_bar.visibility = View.GONE
//                countdown_tv.visibility = View.GONE
//                (timer as CountDownTimer).cancel()
//            }
//        }.start()
//    }
private fun startTimer(time_in_seconds: Long) {
    timer = object : CountDownTimer(time_in_seconds, 1000) {
        override fun onFinish() {
            request_sms_btn.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
                countdown_tv.visibility = View.GONE

                (timer as CountDownTimer).cancel()
        }

        override fun onTick(p0: Long) {
            time_in_milli_seconds = p0
            request_sms_btn.visibility = View.GONE
                progress_bar.visibility = View.VISIBLE
                countdown_tv.visibility = View.VISIBLE
                countdown_tv.text = timeFormat.format(time_in_milli_seconds)
                Log.d("secund",""+timeFormat.format(time_in_milli_seconds))
        }
    }
        (timer as CountDownTimer).start()
    isRunning = true
}

    }

