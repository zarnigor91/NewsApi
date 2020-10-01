//package com.example.marta.ui
//
//import android.content.Context
//import android.os.Build
//import android.os.Bundle
//import android.os.VibrationEffect
//import android.os.Vibrator
//import android.view.View
//import android.view.animation.AnimationUtils
//import android.widget.Button
//import android.widget.ImageButton
//import androidx.annotation.RequiresApi
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import butterknife.OnClick
//import com.example.marta.R
//import com.example.marta.utils.MdUtils
//import kotlinx.android.synthetic.main.fragment_password_settings.*
//
//
//
//class SettingPassFragment : Fragment(R.layout.fragment_password_settings) {
//    private var numPassword:String?=null
//    private var pin = ""
//    private var storedPin: String? = null
//    private val stateIsNew = false
//    private var pinAttempts = 0
//    private var mPinNumbers = arrayListOf<Int>()
//    private var bt1:Button?=null
//    private var bt2:Button?=null
//    private var bt3:Button?=null
//    private var bt4:Button?=null
//    private var bt5:Button?=null
//    private var bt6:Button?=null
//    private var bt7:Button?=null
//    private var bt8:Button?=null
//    private var bt9:Button?=null
//    private var bt0:Button?=null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        val include: View = requireView().findViewById(R.id.num_pad_view)
//        bt1 = include.findViewById(R.id.d1) as Button
//         bt2 = include.findViewById(R.id.d1) as Button
//        bt3 = include.findViewById(R.id.d1) as Button
//        bt4 = include.findViewById(R.id.d1) as Button
//         bt5 = include.findViewById(R.id.d1) as Button
//         bt6 = include.findViewById(R.id.d1) as Button
//         bt7 = include.findViewById(R.id.d1) as Button
//         bt8 = include.findViewById(R.id.d1) as Button
//        bt9 = include.findViewById(R.id.d1) as Button
//         bt0 = include.findViewById(R.id.d0) as Button
//        val btnBask=include.findViewById(R.id.backspace) as ImageButton
//
////        bt1!!.setOnClickListener(this)
////        bt2!!.setOnClickListener(this)
////        bt3!!.setOnClickListener(this)
////        bt4!!.setOnClickListener(this)
////        bt5!!.setOnClickListener(this)
////        bt6!!.setOnClickListener(this)
////        btnBask.setOnClickListener(this)
//
//
////        val bt1 = include.findViewById(R.id.d1) as Button
//        val numList = ArrayList<Button>()
////        numList.add(bt1)
////        numList.add(bt2)
////        numList.add(bt3)
////        numList.add(bt4)
////        numList.add(bt5)
////        numList.add(bt6)
////        numList.add(bt7)
////        numList.add(bt8)
////        numList.add(bt9)
////        numList.add(bt0)
////
////        for (i in numList.indices)
////            onPinBtnClick(numList[i])
//        for (i in numList.indices)
//            onBackspaceClick(numList[i])
//    }
//
//    override fun onResume() {
//        super.onResume()
//        resetPin()
//    }
//        private fun click(){
//            val include: View = requireView().findViewById(R.id.num_pad_view)
//            val bt1 = include.findViewById(R.id.d1) as Button
//            val bt2 = include.findViewById(R.id.d2) as Button
//            val bt3 = include.findViewById(R.id.d3) as Button
//            val bt4 = include.findViewById(R.id.d4) as Button
//            val bt5 = include.findViewById(R.id.d5) as Button
//            val bt6 = include.findViewById(R.id.d6) as Button
//            val bt7 = include.findViewById(R.id.d7) as Button
//            val bt8 = include.findViewById(R.id.d8) as Button
//            val bt9 = include.findViewById(R.id.d9) as Button
//            val bt0 = include.findViewById(R.id.d0) as Button
//
////        val bt1 = include.findViewById(R.id.d1) as Button
//            val numList = ArrayList<Button>()
//            numList.add(bt1)
//            numList.add(bt2)
//            numList.add(bt3)
//            numList.add(bt4)
//            numList.add(bt5)
//            numList.add(bt6)
//            numList.add(bt7)
//            numList.add(bt8)
//            numList.add(bt9)
//            numList.add(bt0)
//
//
//
//
//            for (i in numList.indices)
//                onPinBtnClick(numList[i])
//            for (i in numList.indices)
//                onBackspaceClick(numList[i])
//        }
//
//    fun addToPinCode(btnVal: String) {
//        updatePinDotsView(pin.length, true)
//        pin += btnVal
//        if (pin.length < 4) return
//        val hashedPin: String? =  MdUtils.md5(MdUtils.md5(pin).toString() + pin)
//        pin = ""
//        if (stateIsNew && storedPin!!.isEmpty()) {
//            storedPin = hashedPin
//           repeatPin()
//        } else if (storedPin != hashedPin) {
//         pinNotEqual()
//            pinAttempts++
//            if (pinAttempts == 3) {
//                storedPin = ""
//               moveToPasswordFragment()
//            }
//        } else {
////            activityCallback.onPinCompleted(hashedPin, stateIsNew)
//        }
//    }
//
////    @OnClick(
////        R.id.d1,
////        R.id.d2,
////        R.id.d3,
////        R.id.d4,
////        R.id.d5,
////        R.id.d6,
////        R.id.d7,
////        R.id.d8,
////        R.id.d9,
////        R.id.d0
//////    )
////  private  fun onPinBtnClick(view: View) {
////        addToPinCode(view.tag.toString())
////    }
////    override fun onClick(p0: View?) {
////       onPinBtnClick(p0!!)
//////        onBackspaceClick(p0)
////    }
//  private  fun pinNotEqual() {
//        resetPin()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            vibrate(400L)
//        }
//        pin_dots_view.startAnimation(
//            AnimationUtils.loadAnimation(
//                context,
//                R.anim.shake_dots
//            )
//        )
//    }
//
//   private fun removeFromPinCode() {
//        if (null == pin || pin.isEmpty()) return
//        pin = pin.substring(0, pin.length - 1)
//       updatePinDotsView(pin.length, false)
//    }
//
//  private  fun updatePinDotsView(position: Int, isAdded: Boolean) {
//        val circle: View = pin_dots_view.getChildAt(position)
//        if (null != circle) circle.background = ContextCompat.getDrawable(
//           requireContext()!!,
//            if (isAdded) R.drawable.bg_indicator else R.drawable.bg_indicator_empty
//        )
//    }
//
//
//
//    @OnClick(R.id.backspace)
//   private fun onBackspaceClick(view: View?) {
//       removeFromPinCode()
//    }
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun vibrate(ms: Long) {
//        val v =
//          requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//        v.vibrate(VibrationEffect.createOneShot(ms, VibrationEffect.DEFAULT_AMPLITUDE))
//    }
//
//    private fun resetPin() {
//        for (i in 0 until pin_dots_view.childCount) {
//            val drawable =
//                ContextCompat.getDrawable(
//                    requireContext(),
//                    R.drawable.bg_indicator_empty
//                )
//            pin_dots_view.getChildAt(i).background = drawable
//        }
//    }
//  private  fun repeatPin() {
//        try {
//            Thread.sleep(200L)
//        } catch (e: InterruptedException) {
//        }
////        pinHintTv.setText(R.string.repeat_enter_new_pin_code)
//        resetPin()
//    }
//   private fun moveToPasswordFragment() {
////       .onRequestError(getString(R.string.wrong_pin_attempts))
////        (activity as ConfigActivity?).initFragment(PasswordFragment.newInstance(false))
//    }
//
//
//
////   private fun onNumberClick(number: Int) {
////        mPinNumbers.add(number)
////        viewState.setSelectPinIndicators(mPinNumbers.size)
////        if (mPinNumbers.size == 4) {
////            checkPinCode()
////        }
////    }
////
////    private fun isKeyGenerated(): Boolean {
////        return try {
////            val keyStore =
////                KeyStore.getInstance("AndroidKeyStore")
////            val keyGenerator = KeyGenerator.getInstance(
////                KeyProperties.KEY_ALGORITHM_AES,
////                "AndroidKeyStore"
////            )
////            keyStore.load(null)
////            val builder = KeyGenParameterSpec.Builder(
////                "FP_KEY",
////                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
////            )
////            val specs = builder.setBlockModes(KeyProperties.BLOCK_MODE_CBC)
////                .setUserAuthenticationRequired(true)
////                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
////                .build()
////            keyGenerator.init(specs)
////            keyGenerator.generateKey()
////            true
////        } catch (e: NoSuchAlgorithmException) {
////            e.printStackTrace()
////            false
////        } catch (e: InvalidAlgorithmParameterException) {
////            e.printStackTrace()
////            false
////        } catch (e: KeyStoreException) {
////            e.printStackTrace()
////            false
////        } catch (e: CertificateException) {
////            e.printStackTrace()
////            false
////        } catch (e: IOException) {
////            e.printStackTrace()
////            false
////        } catch (e: NoSuchProviderException) {
////            e.printStackTrace()
////            false
////        }
////    }
//
//
//
//
//}