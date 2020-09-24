package com.example.marta.vm

import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.marta.R
import com.example.marta.model.VerRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfirmViewModel:ViewModel(){
    private lateinit var api: PostApi
    private lateinit var preference: PreferencesUtil

    fun init(api: PostApi, preferencesUtil: PreferencesUtil) {
        this.api = api
        this.preference = preferencesUtil
    }

    private val _confrimLiveData=MutableLiveData<String>()
    val smsLiveData:LiveData<String> = _confrimLiveData

    fun loadSms(verRequest: VerRequest){
        viewModelScope.launch(Dispatchers.IO) {
            val smsresponce = api.getPosts(VerRequest(verRequest.phone))
            if (smsresponce.isSuccessful){
                _confrimLiveData.postValue(smsresponce.body()?.hash)
            }
            else{
                _confrimLiveData.postValue("")
            }

        }
    }


}
fun Fragment.confirmViewMOdel() = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ConfirmViewModel::class.java)