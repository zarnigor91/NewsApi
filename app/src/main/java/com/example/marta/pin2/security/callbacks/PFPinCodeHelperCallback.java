package com.example.marta.pin2.security.callbacks;


import com.example.marta.pin2.security.PFResult;

public interface PFPinCodeHelperCallback<T> {
    void onResult(PFResult<T> result);
}