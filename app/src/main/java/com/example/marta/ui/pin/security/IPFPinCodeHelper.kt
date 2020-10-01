package com.example.marta.ui.pin.security

import android.content.Context
import com.example.marta.ui.pin.security.calbacs.PFPinCodeHelperCallback


interface IPFPinCodeHelper {

    fun encodePin(
        context: Context?,
        pin: String?,
        callBack: PFPinCodeHelperCallback<String>
    )

    fun checkPin(
        context: Context?,
        encodedPin: String?,
        pin: String?,
        callback: PFPinCodeHelperCallback<PFResult<Boolean>>
    )

    fun delete(callback: PFPinCodeHelperCallback<PFResult<Boolean>>)

    fun isPinCodeEncryptionKeyExist(callback: PFPinCodeHelperCallback<PFResult<Boolean>>)
}