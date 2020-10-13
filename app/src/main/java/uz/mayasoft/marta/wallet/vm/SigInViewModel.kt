    package uz.mayasoft.marta.wallet.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import uz.mayasoft.marta.wallet.model.LoginRequest
import uz.mayasoft.marta.wallet.model.LoginResponce
import uz.mayasoft.marta.wallet.model.SigUpRequest
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SigUpViewModel :ViewModel(){
    private lateinit var api: PostApi
    private lateinit var preference: PreferencesUtil

    fun init(api: PostApi, preferencesUtil: PreferencesUtil) {
        this.api = api
        this.preference = preferencesUtil
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

    private val _tokenLiveData = MutableLiveData<LoginResponce>()
    val tokenLiveData: LiveData<LoginResponce> = _tokenLiveData

    fun getToken(loginRequest: LoginRequest) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getToken(loginRequest.username, loginRequest.password, loginRequest.grant_type)

            if (response.isSuccessful) {
                preference.setAcsessToken(response.body()?.accessToken.toString())
                preference.setRefreshToken(response.body()?.refreshToken.toString())
                preference.setLogin(true)
                _tokenLiveData.postValue(response.body())

            } else {
                _tokenLiveData.postValue(response.body())
            }
        }
    }

}
fun Fragment.sipUpViewModel() = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
    SigUpViewModel::class.java)