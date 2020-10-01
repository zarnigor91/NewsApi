//package com.example.marta.ui.pin.security
//
//import android.content.Context
//import androidx.core.hardware.fingerprint.FingerprintManagerCompat
//
//import com.example.marta.ui.pin.security.calbacs.PFPinCodeHelperCallback
//
//class PFFingerprintPinCodeHelper private constructor() : IPFPinCodeHelper {
//    private val pfSecurityUtils: IPFSecurityUtils =
//        PFSecurityUtilsFactory.pFSecurityUtilsInstance
//
//    private fun isFingerPrintAvailable(context: Context): Boolean {
//        return FingerprintManagerCompat.from(context)
//            .isHardwareDetected
//    }
//
//    private fun isFingerPrintReady(context: Context): Boolean {
//        return FingerprintManagerCompat.from(context)
//            .hasEnrolledFingerprints()
//    }
//
//
//
//    companion object {
//        private const val FINGERPRINT_ALIAS = "fp_fingerprint_lock_screen_key_store"
//        private const val PIN_ALIAS = "fp_pin_lock_screen_key_store"
//        val instance = PFFingerprintPinCodeHelper()
//    }
//
//    override fun encodePin(
//        context: Context?,
//        pin: String?,
//        callBack: PFPinCodeHelperCallback<String>
//    ) {
//        try {
//            val encoded = pfSecurityUtils.encode(
//                context!!,
//                PIN_ALIAS,
//                pin,
//                false
//            )
//            callBack.onResult(PFResult(encoded))
//        } catch (e: PFSecurityException) {
//            callBack.onResult(PFResult(e.error))
//        }
//    }
//
//    override fun checkPin(
//        context: Context?,
//        encodedPin: String?,
//        pin: String?,
//        callback: PFPinCodeHelperCallback<PFResult<Boolean>>
//    ) {
//        try {
//            val pinCode =
//                pfSecurityUtils.decode(PIN_ALIAS, encodedPin)
//            callback.onResult(PFResult(pinCode.equals(pin)))
//        } catch (e: PFSecurityException) {
//            callback?.onResult(PFResult(e.error))
//        }
//    }
//
//    override fun delete(callback: PFPinCodeHelperCallback<PFResult<Boolean>>) {
//        try {
//            pfSecurityUtils.deleteKey(PIN_ALIAS)
//            callback?.onResult(PFResult(true))
//        } catch (e: PFSecurityException) {
//            callback?.onResult(PFResult(e.error))
//        }
//    }
//
//    override fun isPinCodeEncryptionKeyExist(callback: PFPinCodeHelperCallback<PFResult<Boolean>>) {
//        try {
//            val isExist =
//                pfSecurityUtils.isKeystoreContainAlias(PIN_ALIAS)
//            callback?.onResult(PFResult(isExist))
//        } catch (e: PFSecurityException) {
//            callback?.onResult(PFResult(e.error))
//        }
//    }
//}
//
