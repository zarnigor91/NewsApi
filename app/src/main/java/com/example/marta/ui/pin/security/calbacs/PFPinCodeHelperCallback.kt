package com.example.marta.ui.pin.security.calbacs

import com.example.marta.ui.pin.security.PFResult

interface PFPinCodeHelperCallback<T> {
    fun onResult(result:T)
}