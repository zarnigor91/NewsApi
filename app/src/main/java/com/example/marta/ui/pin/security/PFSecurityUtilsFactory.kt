package com.example.marta.ui.pin.security

import android.os.Build

object PFSecurityUtilsFactory {
    val pFSecurityUtilsInstance: IPFSecurityUtils
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PFSecurityUtils.instance
        } else {
            PFSecurityUtilsOld.instance
        }
}