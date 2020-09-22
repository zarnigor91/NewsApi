package com.example.marta.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.marta.ui.language.LanguageActivity
import com.example.marta.model.VerRequest
import com.example.marta.network.PostApi
import com.example.marta.utils.PreferencesUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel :ViewModel(){

    private lateinit var preference: PreferencesUtil
    private lateinit var api: PostApi

    fun init(api: PostApi, preferencesUtil: PreferencesUtil) {
        this.api = api
        this.preference = preferencesUtil
    }

    private val _tokenLiveData = MutableLiveData<String>()
    val tokenLiveData: LiveData<String> = _tokenLiveData

//    fun getToken(createLogin: LoginRequest) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = api.getPosts(  createLogin.username, createLogin.password, createLogin.grant_type)
//
//            if (response.isSuccessful) {
//                _tokenLiveData.postValue(response.body()?.accessToken)
//            } else {
//                _tokenLiveData.postValue("")
//            }
//        }
//    }
    fun getHash(createLogin:VerRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getPosts(VerRequest(createLogin.phone))

            if (response.isSuccessful) {
                _tokenLiveData.postValue(response.body()?.hash)

            } else {
                _tokenLiveData.postValue("")
            }
        }
    }
}

fun Fragment.loginViewModel() = ViewModelProviders.of(activitySplash())[LoginViewModel::class.java]
fun Fragment.activitySplash() = activity as LanguageActivity
