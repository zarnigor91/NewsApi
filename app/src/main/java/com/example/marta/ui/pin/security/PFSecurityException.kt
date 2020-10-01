package com.example.marta.ui.pin.security

import com.example.marta.ui.pin.security.PFSecurityError

class PFSecurityException(
    message: String?,
    val code: Int
) :
    Exception(message) {
    val error: PFSecurityError
        get() = PFSecurityError(
            message!!,
            code
        )
}
