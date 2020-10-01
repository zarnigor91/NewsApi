package com.example.marta.ui.pin.security.liveData

import androidx.lifecycle.LiveData

class PFLiveData<T> : LiveData<T>() {
    fun setData(data: T) {
        value = data
    }
}