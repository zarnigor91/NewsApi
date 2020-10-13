package uz.mayasoft.marta.wallet.ui.language

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import uz.mayasoft.marta.wallet.PREF_USER
import uz.mayasoft.marta.wallet.app.App
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.ui.FirstFragment
import java.util.*

class LanguageActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

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