//package com.example.marta.ui.pin.viewmodels
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.example.marta.ui.pin.security.PFResult
//import com.example.marta.ui.pin.security.PFSecurityManager
//import com.example.marta.ui.pin.security.calbacs.PFPinCodeHelperCallback
//import com.example.marta.ui.pin.security.liveData.PFLiveData
//
//
//class PFPinCodeViewModel : ViewModel() {
//    fun encodePin(
//        context: Context?,
//        pin: String?
//    ): LiveData<PFResult<String>> {
//        val liveData: PFLiveData<PFResult<String>> = PFLiveData()
//        PFSecurityManager.instance.pinCodeHelper.encodePin(
//            context,
//            pin,
//            object : PFPinCodeHelperCallback<String> {
//                fun onResult(result: PFResult<Boolean>) {
//                    liveData.setData(result)
//                }
//
//                override fun onResult(result: PFResult<String>) {
//                    TODO("Not yet implemented")
//                }
//            }
//        )
//        return liveData
//    }
//
//    fun checkPin(
//        context: Context?,
//        encodedPin: String?,
//        pin: String?
//    ): LiveData<PFResult<Boolean>> {
//        val liveData: PFLiveData<PFResult<Boolean>> = PFLiveData()
//        PFSecurityManager.instance.pinCodeHelper.checkPin(
//            context,
//            encodedPin,
//            pin,
//            object : PFPinCodeHelperCallback<Boolean?> {
//                fun onResult(result: PFResult<Boolean?>?) {
//                    liveData.setData(result)
//                }
//            }
//        )
//        return liveData
//    }
//
//    fun delete(): LiveData<PFResult<Boolean>> {
//        val liveData: PFLiveData<PFResult<Boolean>> = PFLiveData()
//        PFSecurityManager.instance.pinCodeHelper.delete(
//            object : PFPinCodeHelperCallback<Boolean?>() {
//                fun onResult(result: PFResult<Boolean?>?) {
//                    liveData.setData(result)
//                }
//            }
//        )
//        return liveData
//    }
//
//    val isPinCodeEncryptionKeyExist: LiveData<Any>
//        get() {
//            val liveData: PFLiveData<PFResult<Boolean>> = PFLiveData()
//            PFSecurityManager.instance.pinCodeHelper.isPinCodeEncryptionKeyExist(
//                object : PFPinCodeHelperCallback<Boolean?>() {
//                    fun onResult(result: PFResult<Boolean?>?) {
//                        liveData.setData(result)
//                    }
//                }
//            )
//            return liveData
//        }
//}