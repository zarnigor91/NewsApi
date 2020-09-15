package com.example.marta



import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.marta.bio.VerificationFragment
import com.example.marta.bio.yan
import kotlinx.android.synthetic.main.language_layout.*
import java.util.*
class LanguageFragment :Fragment(R.layout.language_layout){
    var sharedPref=PreferenceManager.getDefaultSharedPreferences(App.getApplication())
    var sharedPrefen=PreferenceManager.getDefaultSharedPreferences(App.getApplication())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rbchek = sharedPref.getBoolean("rb", true)
        val lan = sharedPref.getString("language", "")
        if (lan!!.isNotEmpty()) {
            when (lan) {
                "en" -> {
                    bt_ru.isSelected = true
                }

                "ru" -> {
                    bt_en.isSelected = true
                }
                else -> {
                    bt_ar.isSelected = true
                }
            }
        } else bt_ru.isSelected = true



        bt_en.setOnClickListener {
            nextFragment()
            changeLang("en")
            bt_en.isSelected = true
            bt_ru.isSelected = false
            bt_ar.isSelected=false
            sharedPref.edit().putString("language", "en").apply()
        }
        bt_ru.setOnClickListener {
            nextFragment()
            changeLang("ru")
            bt_en.isSelected = false
            bt_ru.isSelected = true
            bt_ar.isSelected=false
            sharedPref.edit().putString("language", "ru").apply()

        }

        bt_ar.setOnClickListener {
            nextFragment()
            changeLang("ar")
            bt_en.isSelected = false
            bt_ru.isSelected = false
            bt_ar.isSelected=true
            sharedPref.edit().putString("language", "ar").apply()

        }
    }
   private fun nextFragment(){
       fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
           R.id.container2,
           LoginFragment()
       )?.commit()
   }
    private fun changeLang(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
     sharedPref.edit().putString("language", language).apply()
        val resources = activity?.resources
        val dm = resources?.displayMetrics
        val configuration = resources?.configuration
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration?.setLocale(locale)
        }else{
            configuration?.locale = locale
        }
        resources?.updateConfiguration(configuration, dm)
//        activity?.recreate()
    }
}