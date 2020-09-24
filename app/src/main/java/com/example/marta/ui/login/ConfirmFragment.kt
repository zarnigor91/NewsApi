package com.example.marta.ui.login


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.marta.R
import com.example.marta.app.App
import com.example.marta.model.VerRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import com.example.marta.vm.confirmViewMOdel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_otp.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ConfirmFragment : Fragment(R.layout.fragment_otp) {
    @Inject
    lateinit var api: PostApi

    private lateinit var pwdOtpEt: EditText
    private lateinit var pwdOtpTil: TextInputLayout
    private var timer: CountDownTimer? = null
    var isRunning: Boolean = false
    var time_in_milli_seconds = 0L
    private val timeFormat by lazy(LazyThreadSafetyMode.NONE) {
        SimpleDateFormat("ss", Locale.getDefault())
    }
    @Inject
    lateinit var preferencesUtil: PreferencesUtil
    private var stringArray: Array<String> = emptyArray()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependency(this)

        val bundle = arguments
         stringArray = arguments?.getStringArray("works") as Array<String>



        val sigUpFragment = SigUpFragment()
        startTimer(60000)
        pwdOtpEt = view.findViewById(R.id.otp_password) as EditText
        pwdOtpTil = view.findViewById(R.id.layout_otp_password) as TextInputLayout


        next_btn.setOnClickListener {
            if (validate(pwdOtpEt, pwdOtpTil)) {
                bundle?.putString("conf", pwdOtpEt.text.toString())
                sigUpFragment.arguments = bundle
                timer?.cancel()
                activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
                    R.anim.enter_left,
                    R.anim.exit_left,
                    R.anim.enter_right,
                    R.anim.exit_right
                )?.addToBackStack(null)?.replace(
                    R.id.container2,
                    sigUpFragment
                )?.commit()
            }
        }

        request_sms_btn.setOnClickListener {
            startTimer(60000)
            Log.d("tel",""+stringArray[1])
            confirmViewMOdel().loadSms(VerRequest(stringArray[1]))
            loadData()
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
                Log.d("secund", "" + timeFormat.format(time_in_milli_seconds))

            }
        }.start()
//        (timer as CountDownTimer).start()
//        isRunning = true

    }

    private fun injectDependency(fragment: ConfirmFragment) {
        (activity?.application as App).getApiComponent().inject(fragment)
    }

    private fun loadData(){
        confirmViewMOdel().init(api, preferencesUtil)
        confirmViewMOdel().smsLiveData.observe(viewLifecycleOwner, smsObserver)
    }
    private val smsObserver=Observer<String>{
        if (it.isNullOrEmpty()){
            stringArray[0]=it
            preferencesUtil.clearToken(it)
        }
        else{
            Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
        }
    }

}

