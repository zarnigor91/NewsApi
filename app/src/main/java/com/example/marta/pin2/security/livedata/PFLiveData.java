package com.example.marta.pin2.security.livedata;

import androidx.lifecycle.LiveData;

public class PFLiveData<T> extends LiveData<T> {
    public void setData(T data) {
        setValue(data);
    }
}
