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

      when(lan){
          "uz"-> { rb_uz.isChecked=true}
          "ru"->{rb_rus.isChecked=true}
          "en"-> {rb_en.isChecked=true}
          else->{rb_arab.isChecked=true}
      }
          bt_next.setOnClickListener {
              fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
                  R.id.container2,
                 VerificationFragment()
              )?.commit()
          }
//        if (lan!!.isNotEmpty()){

        rg_lan.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb_uz-> {
                    rb_uz.isChecked=true
                    sharedPrefen.edit().putBoolean("rb", true).apply()
                    changeLang("uz")
                    bt_next.text="Keyingiisi"
                }
                R.id.rb_rus -> {
                    rb_rus.isChecked=true
                    changeLang("ru")
                    bt_next.text="Sledushiy"
                }
                R.id.rb_en -> {
                    changeLang("en")
                    bt_next.text="Next"
                }
                else -> { changeLang("ar")
                    bt_next.text="Keyingiisi"
                }
            }
        }

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
        activity?.recreate()
    }
}