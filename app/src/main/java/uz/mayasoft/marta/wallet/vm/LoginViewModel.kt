package uz.mayasoft.marta.wallet.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import uz.mayasoft.marta.wallet.model.LoginRequest
import uz.mayasoft.marta.wallet.model.LoginResponce
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    private lateinit var preference: PreferencesUtil
    private lateinit var api: PostApi

    fun init(api: PostApi, preferencesUtil: PreferencesUtil) {
        this.api = api
        this.preference = preferencesUtil
    }

    private val _tokenLiveData = MutableLiveData<LoginResponce>()
    val tokenLiveData: LiveData<LoginResponce> = _tokenLiveData

        fun getToken(loginRequest: LoginRequest) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getToken(loginRequest.username, loginRequest.password, loginRequest.grant_type)

            if (response.isSuccessful) {
                preference.setAcsessToken(response.body()?.accessToken.toString())
                preference.setRefreshToken(response.body()?.refreshToken.toString())
                _tokenLiveData.postValue(response.body())


            } else {
                _tokenLiveData.postValue(response.body())
            }
        }
    }
}
fun Fragment.loginViewModel() = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
    LoginViewModel::class.java)
//fun Fragment.loginViewModel() = ViewModelProviders.of(activitySplash())[LoginViewModel::class.java]
//fun Fragment.activitySplash() = activity as LanguageActivity