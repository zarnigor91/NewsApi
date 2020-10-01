package com.example.marta.ui.pin.security

import com.example.marta.pin2.security.PFFingerprintPinCodeHelper
import com.example.marta.ui.pin.security.PFSecurityManager.Companion.instance

class PFSecurityManager private constructor() {
//    var pinCodeHelper: IPFPinCodeHelper = PFFingerprintPinCodeHelper.getInstance()

    companion object {
        val instance = PFSecurityManager()
    }
}