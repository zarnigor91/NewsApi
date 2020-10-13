package uz.mayasoft.marta.wallet.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.app.App
import uz.mayasoft.marta.wallet.ui.language.LanguageActivity
import uz.mayasoft.marta.wallet.ui.login.LoginActivity
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import java.util.*
import javax.inject.Inject


class SplashActivity : AppCompatActivity() {

    //    private lateinit var viewModel: LoginViewModel
//    private var acsess: AcsessTokenResponce?=null
//    private var acsessToken: LoginResponce?=null
//    @Inject
//    lateinit var api: PostApi
//     lateinit var tokenViewModel: TokenViewModel
    @Inject
    lateinit var tokenPreferencesUtil: PreferencesUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.splash_activity)
//        viewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]

        val locale = Locale("uz")
        Locale.setDefault(locale)
        val configuration = this.resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }
        this.resources.updateConfiguration(configuration, this.resources.displayMetrics)


        val languageActivityIntent = Intent(this, LanguageActivity::class.java)
        languageActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        val loginActivityIntent = Intent(this, LoginActivity::class.java)
        loginActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

        injectDependency(this)

        if (tokenPreferencesUtil.isLogin()) {
            Handler().postDelayed(
                {
                    startActivity(loginActivityIntent)
                    finish()
                }, 300
            )

//           loadToken()

        } else {
            Handler().postDelayed(
                {
                    startActivity(languageActivityIntent)
                    finish()
                }, 300
            )
        }

    }

    //       private fun check(){
//
//           if (acsess!=null){
//               supportFragmentManager.beginTransaction().setCustomAnimations(
//                   R.anim.enter_left,
//                   R.anim.exit_left,
//                   R.anim.enter_right,
//                   R.anim.exit_right
//               ).addToBackStack(null).replace(
//                   R.id.container2,
//                   PinPasFragment()
//               ).commit()
//           }
//           else
//           {
//               loadRefreshToken()
//               tokenViewModel.getAcsessToken(RefreshTokenRequest("refresh_token",tokenPreferencesUtil.getRefreshToken()))
//               if (acsessToken!=null)
//               {
////                  tokenPreferencesUtil.setAcsessToken(acsessToken!!.accessToken.toString())
////                  activity?.supportFragmentManager?.beginTransaction()?.setCustomAnimations(
////                      R.anim.enter_left,
////                      R.anim.exit_left,
////                      R.anim.enter_right,
////                      R.anim.exit_right
////                  )?.addToBackStack(null)?.replace(
////                      R.id.container2,
////                      PinPasFragment()
////                  )?.commit()
//                   val intent = Intent(this, DashboardActivity::class.java)
//                   intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                   startActivity(intent)
//               }
//               else{
////                  supportFragmentManager.beginTransaction().setCustomAnimations(
////                       R.anim.enter_left,
////                       R.anim.exit_left,
////                       R.anim.enter_right,
////                       R.anim.exit_right
////                   ).addToBackStack(null).replace(
////                       R.id.container2,
////                       LoginFragment()
////                   ).commit()
//               }
//           }
//       }
//
//    private fun loadToken(){
//
//        tokenViewModel.init(api)
//        tokenViewModel.acsessToken.observe(this, androidx.lifecycle.Observer {
//            acsess=it
//            check()
//        })
//        tokenViewModel.chekToken(AcsessTokenRequest(tokenPreferencesUtil.getAcsessToken()))
//
//    }
    private fun injectDependency(activity: SplashActivity) {
        (this.application as App).getApiComponent().inject(activity)
    }
//    private fun loadRefreshToken(){
//        tokenViewModel.init(api)
//        tokenViewModel.refreshToken.observe(this,androidx.lifecycle.Observer{
////            Log.d("login",""+it.accessToken)
//            acsessToken=it
//        })
//    }
}
