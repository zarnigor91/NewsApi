package com.example.marta.ui.language

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.marta.PREF_USER
import com.example.marta.app.App
import com.example.marta.R
import com.example.marta.ui.FirstFragment
import com.example.marta.ui.login.LoginFragment
import java.util.*

class LanguageActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

        if (isLogIn() != null) {
          supportFragmentManager.beginTransaction()
            .replace(
                R.id.container2,
                LoginFragment()
            )
            .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container2,
                    LanguageFragment()
                )
                .commit()
        }
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container2,
               FirstFragment()
            )
            .commit()
        val prefShar = PreferenceManager.getDefaultSharedPreferences(App.getApplication())
        val lan = prefShar.getString("language", "")
        val locale = Locale(lan)
        Locale.setDefault(locale)
        val resources = this.resources
        val dm = resources.displayMetrics
        val configuration = resources.configuration
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(locale)
        }else{
            configuration.locale = locale
        }
        resources.updateConfiguration(configuration, dm)

    }
    private fun isLogIn(): String? {
        return PreferenceManager.getDefaultSharedPreferences(App.getApplication())
            .getString(PREF_USER, null)

    }
}