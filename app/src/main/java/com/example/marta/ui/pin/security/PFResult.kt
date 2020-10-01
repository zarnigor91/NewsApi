package com.example.marta.ui.pin.security


class PFResult<T> {
    var error: PFSecurityError? = null
        private set
    var result: T? = null
        private set
    constructor(mError: PFSecurityError?) {
        error = mError
    }
    constructor(_result: T?) {
        result = _result
    }
}