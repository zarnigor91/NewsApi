//package uz.mayasoft.marta.wallet.ui.login
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.Observer
//import androidx.lifecycle.observe
//
//import uz.mayasoft.marta.wallet.R
//import uz.mayasoft.marta.wallet.App
//import uz.mayasoft.marta.wallet.model.*
//
//import uz.mayasoft.marta.wallet.PostApi
//import uz.mayasoft.marta.wallet.PinPasFragment
//import uz.mayasoft.marta.wallet.DashboardActivity
//import uz.mayasoft.marta.wallet.ui.sig_up.SigUpFragment
//import uz.mayasoft.marta.wallet.PreferencesUtil
//
//import uz.mayasoft.marta.wallet.vm.tokenViewModel
//import javax.inject.Inject
//
//class TokenFragment :Fragment(){
//     private var acsess:AcsessTokenResponce?=null
//    private var acsessToken:LoginResponce?=null
//    @Inject
//    lateinit var api: PostApi
//
//    @Injectepaoegdrijoeigrj
//    lateinit var tokenPreferencesUtil: PreferencesUtil
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        injectDependency(this)
//        loadToken()
//        tokenViewModel().chekToken(AcsessTokenRequest(tokenPreferencesUtil.getAcsessToken()))
//          Log.d("acsessss"," "+tokenPreferencesUtil.getAcsessToken())
//
//    }
//
//    private fun loadToken(){
//        tokenViewModel().init(api)
//        tokenViewModel().acsessToken.observe(this, Observer{
//          acsess=it
//            Log.d("acsNull",""+it)
//
//        })
//    }
//    private fun injectDependency(fragment: TokenFragment) {
//        (activity?.application as App).getApiComponent().inject(fragment)
//    }
//    private fun loadRefreshToken(){
//        tokenViewModel().init(api)
//        tokenViewModel().refreshToken.observe(this, Observer{
//            Log.d("login",""+it.accessToken)
//            acsessToken=it
//        })
//    }
//}