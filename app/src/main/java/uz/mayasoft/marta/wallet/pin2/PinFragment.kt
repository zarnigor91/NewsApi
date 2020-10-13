package uz.mayasoft.marta.wallet.pin2

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.app.App
import uz.mayasoft.marta.wallet.bio.Biometricfragment
import uz.mayasoft.marta.wallet.pin2.security.PFResult
import uz.mayasoft.marta.wallet.pin2.viewmodels.PFPinCodeViewModel
import uz.mayasoft.marta.wallet.pin2.views.PFCodeView
import uz.mayasoft.marta.wallet.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_lock_screen_pf.*


class PinFragment : Fragment(R.layout.fragment_lock_screen_pf) {

    private val TAG = PinFragment::class.java.name

    private val INSTANCE_STATE_CONFIG =
        "com.beautycoder.pflockscreen.instance_state_config"
    private var sharedPref = PreferenceManager.getDefaultSharedPreferences(App.getApplication())
    var mFingerprintButton: View? = null
    var mDeleteButton: View? = null
    var mLeftButton: TextView? = null
    var mNextButton: ImageButton? = null
    var mCodeView: PFCodeView? = null
    var titleView: TextView? = null

    var mUseFingerPrint = true
    var mFingerprintHardwareDetected = false
    var mIsCreateMode = false

    private var mCodeCreateListener: OnPFLockScreenCodeCreateListener? =
        null
    var mLoginListener: OnPFLockScreenLoginListener? =
        null
    var mCode = ""
    var mCodeValidation = ""
    var mEncodedPinCode = ""

    var mConfiguration: PFFLockScreenConfiguration? = null
    var mRootView: View? = null

    val mPFPinCodeViewModel = PFPinCodeViewModel()

    var mOnLeftButtonClickListener: View.OnClickListener? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(INSTANCE_STATE_CONFIG, mConfiguration)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(
            R.layout.fragment_lock_screen_pf, container,
            false
        )
        if (mConfiguration == null) {
            mConfiguration = savedInstanceState!!.getSerializable(
                INSTANCE_STATE_CONFIG
            ) as PFFLockScreenConfiguration?
        }

        mDeleteButton = view.findViewById(R.id.backspace)
//        mLeftButton = view.findViewById(R.id.button_left)
        mNextButton = view.findViewById(R.id.imb1_ok)

//        mFingerprintHardwareDetected = isFingerprintApiAvailable(requireContext())
        mRootView = view
        applyConfiguration(mConfiguration)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDeleteButton?.setOnClickListener(mOnDeleteButtonClickListener)
        mDeleteButton?.setOnLongClickListener(mOnDeleteButtonOnLongClickListener)
        mFingerprintButton?.setOnClickListener(mOnFingerprintClickListener)
        mCodeView = view.findViewById(R.id.code_view)
        initKeyViews(view)
//        if (!mUseFingerPrint) {
//            mFingerprintButton?.visibility = View.GONE
//        }

        fingerprint_iv.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container2, Biometricfragment())?.commit()
        }
    }

    override fun onStart() {
//        if (!mIsCreateMode && mUseFingerPrint && mConfiguration!!.isAutoShowFingerprint &&
//            isFingerprintApiAvailable(requireActivity()) && isFingerprintsExists(requireActivity())
//        ) {
//            mOnFingerprintClickListener.onClick(mFingerprintButton)
//        }
        mCodeView?.setListener(mCodeListener)
        super.onStart()
    }

    fun setConfiguration(configuration: PFFLockScreenConfiguration?) {
        this.mConfiguration = configuration
        applyConfiguration(configuration)
    }

    open fun applyConfiguration(configuration: PFFLockScreenConfiguration?) {
        if (mRootView == null || configuration == null) {
            return
        }
//        titleView = mRootView!!.findViewById(R.id.title_text_view)
//        titleView.setText(configuration.title)
        if (TextUtils.isEmpty(configuration.leftButton)) {
            mLeftButton!!.visibility = View.GONE
        }
//        else {
//            mLeftButton!!.text = configuration.leftButton
//            mLeftButton!!.setOnClickListener(mOnLeftButtonClickListener)
//        }
//        if (!TextUtils.isEmpty(configuration.nextButton)) {
//            mNextButton!!.text = configuration.nextButton
//        }
        mUseFingerPrint = configuration.isUseFingerprint
//        if (!mUseFingerPrint) {
//            mFingerprintButton!!.visibility = View.GONE
//            mDeleteButton!!.visibility = View.VISIBLE
//        }
        mIsCreateMode = mConfiguration!!.mode === PFFLockScreenConfiguration.MODE_CREATE
//        if (mIsCreateMode) {
//            mLeftButton!!.visibility = View.GONE
//            mFingerprintButton!!.visibility = View.GONE
//        }
        if (mIsCreateMode) {
            mNextButton!!.setOnClickListener(mOnNextButtonClickListener)

        } else {
            mNextButton!!.setOnClickListener(null)
        }
        mNextButton!!.visibility = View.INVISIBLE

        mCodeView?.setCodeLength(mConfiguration!!.codeLength)


    }

    open fun initKeyViews(parent: View) {
        parent.findViewById<View>(R.id.d0)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d1)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d2)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d3)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d4)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d5)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d6)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d7)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d8)
            .setOnClickListener(mOnKeyClickListener)
        parent.findViewById<View>(R.id.d9)
            .setOnClickListener(mOnKeyClickListener)
    }

    private val mOnKeyClickListener =
        View.OnClickListener { v ->
            if (v is TextView) {
                val string = v.text.toString()
                if (string.length != 1) {
                    return@OnClickListener
                }
                val codeLength: Int = mCodeView!!.input(string)

                configureRightButton(codeLength)
            }

        }

    private val mOnDeleteButtonClickListener =
        View.OnClickListener {
            val codeLength: Int = mCodeView!!.delete()
            configureRightButton(codeLength)
        }

    private val mOnDeleteButtonOnLongClickListener =
        OnLongClickListener {
            mCodeView?.clearCode()
            configureRightButton(0)
            true
        }

    private val mOnFingerprintClickListener =
        View.OnClickListener {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
//                !isFingerprintApiAvailable(requireActivity())
//            ) {
//                return@OnClickListener
//            }
//            if (!isFingerprintsExists(requireActivity())) {
//                showNoFingerprintDialog()
//                return@OnClickListener
//            }
//            val fragment = PFFingerprintAuthDialogFragment()
//            fragment.show(getFragmentManager(), FINGERPRINT_DIALOG_FRAGMENT_TAG)
//            fragment.setAuthListener(object : PFFingerprintAuthListener() {
//                fun onAuthenticated() {
//                    if (mLoginListener != null) {
//                        mLoginListener.onFingerprintSuccessful()
//                    }
//                    fragment.dismiss()
//                }
//
//                fun onError() {
//                    if (mLoginListener != null) {
//                        mLoginListener.onFingerprintLoginFailed()
//                    }
//                }
//            })
        }

    open fun configureRightButton(codeLength: Int) {
        if (mIsCreateMode) {
            if (codeLength > 0) {
                mDeleteButton!!.visibility = View.VISIBLE
            } else {
                mDeleteButton!!.visibility = View.GONE
            }
            return
        }
        if (codeLength > 0) {
//            mFingerprintButton!!.visibility = View.GONE
            mDeleteButton!!.visibility = View.VISIBLE
            mDeleteButton!!.isEnabled = true
            return
        }
//        if (mUseFingerPrint && mFingerprintHardwareDetected) {
//            mFingerprintButton!!.visibility = View.VISIBLE
//            mDeleteButton!!.visibility = View.GONE
//        } else {
//            mFingerprintButton!!.visibility = View.GONE
//            mDeleteButton!!.visibility = View.VISIBLE
//        }
//        mDeleteButton!!.isEnabled = false


    }

//    open fun isFingerprintApiAvailable(context: Context): Boolean {
//        return FingerprintManagerCompat.from(context)
//            .isHardwareDetected
//    }
//
//    open fun isFingerprintsExists(context: Context): Boolean {
//        return FingerprintManagerCompat.from(context)
//            .hasEnrolledFingerprints()
//    }


    open fun showNoFingerprintDialog() {
        AlertDialog.Builder(getContext())
            .setTitle(R.string.no_fingerprints_title_pf)
            .setMessage(R.string.no_fingerprints_message_pf)
            .setCancelable(true)
            .setNegativeButton(R.string.cancel_pf, null)
            .setPositiveButton(R.string.settings_pf,
                DialogInterface.OnClickListener { dialog, which ->
                    startActivity(
                        Intent(Settings.ACTION_SECURITY_SETTINGS)
                    )
                }).create().show()
    }

    private val mCodeListener: PFCodeView.OnPFCodeListener = object : PFCodeView.OnPFCodeListener {
        override fun onCodeCompleted(code: String) {
            if (mIsCreateMode) {
                mNextButton!!.visibility = View.VISIBLE
                mCode = code
            }
            mCode = code
            sharedPref.edit().putString("code", code).apply()
            Log.d("codd", "" + code)
            mPFPinCodeViewModel.checkPin(context, mEncodedPinCode, mCode).observe(
                this@PinFragment,checkPinObserver
                )
        }

        override fun onCodeNotCompleted(code: String?) {
            if (mIsCreateMode) {
                mNextButton!!.visibility = View.INVISIBLE
                return
            }
        }
    }
    private val checkPinObserver = Observer<PFResult<Boolean?>?> { result->
            if (result == null) {
                return@Observer
            }
            if (result.error != null) {
                return@Observer
            }
            val isCorrect = result.result?:false
            if (mLoginListener != null) {
                if (isCorrect) {
                    mLoginListener!!.onCodeInputSuccessful()
                } else {
                    mLoginListener!!.onPinLoginFailed()
                    errorAction()
                }
            }
            if (!isCorrect && mConfiguration!!.isClearCodeOnError) {
                mCodeView!!.clearCode()
            }

    }

    private val mOnNextButtonClickListener =
        View.OnClickListener {
            if (mConfiguration!!.isNewCodeValidation && TextUtils.isEmpty(
                    mCodeValidation
                )
            ) {
                mCodeValidation = mCode
                cleanCode()
//                titleView!!.text = mConfiguration!!.newCodeValidationTitle
                return@OnClickListener
            }
            if (mConfiguration!!.isNewCodeValidation && !TextUtils.isEmpty(
                    mCodeValidation
                ) && mCode != mCodeValidation
            ) {
                mCodeCreateListener!!.onNewCodeValidationFailed()
//                titleView!!.text = mConfiguration!!.title
                cleanCode()

                val intent = Intent(context, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                return@OnClickListener
            }
            mCodeValidation = ""
            mPFPinCodeViewModel.encodePin(context, mCode).observe(
                this@PinFragment,
               encodePinObserver
            )


        }

    private val encodePinObserver = Observer<PFResult<String>> {
            result ->
        if (result == null) {
            return@Observer

        }
        if (result.error != null) {

//                        Log.d(
//                            PFLockScreenFragment.TAG,
//                            "Can not encode pin code"
//                        )
            deleteEncodeKey()
            return@Observer
        }
        val encodedCode = result.result!!
//                    val encodedCode = result.result
        mCodeCreateListener?.onCodeCreated(encodedCode)

    }

    open fun cleanCode() {
        mCode = ""
        mCodeView?.clearCode()
    }


    open fun deleteEncodeKey() {
        mPFPinCodeViewModel.delete().observe(
            this,
            Observer<PFResult<Boolean>> { result ->
                if (result == null) {
                    return@Observer
                }
                if (result.getError() != null) {
                    Log.d(TAG, "Can not delete the alias")
                    return@Observer
                }
            }
        )
    }

    open fun errorAction() {
        if (mConfiguration!!.isErrorVibration) {
            val v =
                requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            v?.vibrate(400)
        }
        if (mConfiguration!!.isErrorAnimation) {
            val animShake =
                AnimationUtils.loadAnimation(getContext(), R.anim.shake_pf)
            mCodeView?.startAnimation(animShake)
        }
    }

    fun setOnLeftButtonClickListener(onLeftButtonClickListener: View.OnClickListener?) {
        this.mOnLeftButtonClickListener = onLeftButtonClickListener
    }

    interface OnPFLockScreenLoginListener {

        fun onCodeInputSuccessful()

        fun onFingerprintSuccessful()

        fun onPinLoginFailed()

        fun onFingerprintLoginFailed()
    }

    interface OnPFLockScreenCodeCreateListener {

        fun onCodeCreated(encodedCode: String?)

        fun onNewCodeValidationFailed()
    }

    fun setEncodedPinCode(encodedPinCode: String) {
        mEncodedPinCode = encodedPinCode
    }

    fun setLoginListener(listener: OnPFLockScreenLoginListener) {
        mLoginListener = listener
    }

    fun setCodeCreateListener(listener: OnPFLockScreenCodeCreateListener) {
        mCodeCreateListener = listener
    }
    /*private void showFingerprintAlertDialog(Context context) {
        new AlertDialog.Builder(context).setTitle("Fingerprint").setMessage(
                "Would you like to use fingerprint for future login?")
                .setPositiveButton("Use fingerprint", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if (isFingerprintsExists(getContext())) {
                    //PFFingerprintPinCodeHelper.getInstance().encodePin()
                //}
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }*/

}