package com.example.marta.vm

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.marta.model.*
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException

class TokenViewModel : ViewModel() {

    private lateinit var prefence: PreferencesUtil
    private lateinit var api: PostApi

    fun init(api: PostApi) {
        this.api = api

    }

    private val _acsessToken = MutableLiveData<AcsessTokenResponce>()
    val acsessToken: LiveData<AcsessTokenResponce> = _acsessToken

    private val _refreshToken = MutableLiveData<LoginResponce>()
    val refreshToken: LiveData<LoginResponce> = _refreshToken

    fun chekToken(acsessTokenRequest: AcsessTokenRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.chekAcsess(acsessTokenRequest.token)
            try {
                if (response.isSuccessful) {
                    _acsessToken.postValue(response.body())
                } else {
                    _acsessToken.postValue(response.body())
                }
            } catch (e: ExecutionException) {
                Log.d("xato", "$")
            }

        }
    }
    fun getAcsessToken(refreshTokenRequest: RefreshTokenRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getAcsess(refreshTokenRequest.grantType, refreshTokenRequest.refreshToken)
            try {
                if (response.isSuccessful) {
                    _refreshToken.postValue(response.body())
                } else {
                    _refreshToken.postValue(null)
                }
            } catch (e: ExecutionException) {
                Log.d("xato", "$")
            }

        }
    }

}
//fun Activity.tokenViewModel()= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TokenViewModel::class.java)


