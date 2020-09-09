package com.example.marta

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import java.util.*

class LanguageActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container2, LanguageFragment())
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
}