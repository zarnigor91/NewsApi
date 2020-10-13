package uz.mayasoft.marta.wallet.app

import android.app.Application
import uz.mayasoft.marta.wallet.di.component.DaggerUserApiComponent
import uz.mayasoft.marta.wallet.di.component.UserApiComponent
import uz.mayasoft.marta.wallet.di.module.UserApiModule
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import java.util.*
import com.jakewharton.threetenabp.AndroidThreeTen
import javax.inject.Inject

public class App :Application(){
    @Inject
    lateinit var preferencesUtil: PreferencesUtil

    private lateinit var apiComponent: UserApiComponent

    companion object{
        var app:Application?=null
        fun getApplication():Application=
            app!!
    }
    override fun onCreate() {
        super.onCreate()
        app =this
        initLocale("ru")
        AndroidThreeTen.init(this);
        apiComponent = DaggerUserApiComponent.builder()
            .userApiModule(UserApiModule(this))
            .build()
        apiComponent.inject(this)
    }

    private fun initLocale(lan: String) {
        val config = this.resources.configuration
        config.locale= Locale(lan)
        Locale.setDefault(config.locale)
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )
    }

    open fun getApiComponent(): UserApiComponent {
        return apiComponent
    }



//    private lateinit var apiComponent: UserApiComponent
//
//    private var userLanguage: String? = null
//    override fun onCreate() {
//        super.onCreate()
//        instance = this
//        apiComponent = DaggerUserApiComponent.builder()
//            .userApiModule(UserApiModule(this))
//            .build()
//        apiComponent.inject(this)
//    }
//
//    companion object {
//        var instance: App? = null
//            private set
//    }
//    override fun attachBaseContext(context: Context) {
//        super.attachBaseContext(context)
//        MultiDex.install(this)
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        applyLocale(this, preferencesUtil.getUiLocale())
//        super.onConfigurationChanged(newConfig)
//    }
//
//    fun applyLocale(context: Context) {
//        val uiLocale = preferencesUtil.getUiLocale()
//        applyLocale(context, uiLocale)
//    }
//
//    private fun applyLocale(context: Context, language: String) {
//        userLanguage = language
//        val locale = Locale(userLanguage!!, Constants.COUNTRY_CODE_UZB)
//        applyLocale(context, locale)
//
//        val appContext = context.applicationContext
//        if (context !== appContext) {
//            applyLocale(appContext, locale)
//        }
//    }
//
//    private fun applyLocale(context: Context, locale: Locale) {
//        val resources = context.resources
//        val config = resources.configuration
//        when {
//            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
//                val list = Locale.forLanguageTag(userLanguage?:"")
//                config.setLocale(list)
//            }
//            else -> config.setLocale(locale)
//        }
//        resources.updateConfiguration(config, resources.displayMetrics)
//    }
//
//    open fun getApiComponent(): UserApiComponent {
//        return apiComponent
//    }

}

