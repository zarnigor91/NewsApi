package com.example.marta.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.marta.model.SigUpRequest
import com.example.marta.network.PostApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SigUpViewModel :ViewModel(){
    private lateinit var api:PostApi

    fun init(api: PostApi){
        this.api=api
    }

    private  val _sigUpLiveData=MutableLiveData<String>()
    val postLiveData:LiveData<String> = _sigUpLiveData


    fun loadSigUp(sigUpRequest: SigUpRequest){
      viewModelScope.launch(Dispatchers.IO) {
          val responce=api.getSigUp(SigUpRequest(sigUpRequest.hash,sigUpRequest.confirmation_code,sigUpRequest.password))


          if (responce.isSuccessful) {
              _sigUpLiveData.postValue(responce.body()?.success)
          } else {
              _sigUpLiveData.postValue("")
          }

      }

    }
}

fun Fragment.sipUpViewModel() = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SigUpViewModel::class.java)