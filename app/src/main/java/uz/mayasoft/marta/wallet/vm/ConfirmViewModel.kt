package uz.mayasoft.marta.wallet.vm

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.model.VerRequest
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
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
fun Fragment.confirmViewMOdel() = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
    ConfirmViewModel::class.java)