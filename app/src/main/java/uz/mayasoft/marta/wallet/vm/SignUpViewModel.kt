package uz.mayasoft.marta.wallet.vm

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import uz.mayasoft.marta.wallet.model.ModelResponce
import uz.mayasoft.marta.wallet.model.VerRequest
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException

class SignUpViewModel :ViewModel(){

    private lateinit var preference: PreferencesUtil
    private lateinit var api: PostApi

    fun init(api: PostApi, preferencesUtil: PreferencesUtil) {
        this.api = api
        this.preference = preferencesUtil
    }

    private val _hashLiveData = MutableLiveData<ModelResponce>()
    val hashLiveData: LiveData<ModelResponce> = _hashLiveData

    private val _errorLiveData = MutableLiveData<Int>()
    val errorLiveData: LiveData<Int> = _errorLiveData


    fun getHash(createLogin: VerRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getPosts(VerRequest(createLogin.phone))
                Log.d("responcee","$response")
                if (response.isSuccessful) {
                    _hashLiveData.postValue(response.body())
//                    preference.clearHash(response.body()?.hash.toString())
                    Log.d("ifff",_hashLiveData.postValue(response.body()).toString())

                } else {
                    _errorLiveData.postValue(response.code())
                    Log.d("elseeeee",_hashLiveData.postValue(response.body()).toString())
                }
            }
          catch (e:ExecutionException){
              Log.d("xato","$")
          }

        }
    }
}
fun Fragment.signUpViewModel() = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
    SignUpViewModel::class.java)

