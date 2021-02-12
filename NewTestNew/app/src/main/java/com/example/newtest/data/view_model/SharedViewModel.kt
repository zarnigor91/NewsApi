package com.example.newtest.data.view_model

import androidx.lifecycle.MutableLiveData

class SharedViewModel {
    val itemNews = MutableLiveData<Int>()

    fun setMsgBalance(msg: Int){
        itemNews.value = msg
    }
}