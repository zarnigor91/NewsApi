package com.example.marta.ui.language




import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.marta.app.App
import com.example.marta.R
import com.example.marta.pin2.PinFragment
import com.example.marta.pin2.PinPasFragment
import com.example.marta.ui.dashboard.DashboardActivity

import com.example.marta.ui.login.LoginFragment
import kotlinx.android.synthetic.main.language_layout.*
import java.util.*


class LanguageFragment :Fragment(R.layout.language_layout){
    var sharedPref=PreferenceManager.getDefaultSharedPreferences(App.getApplication())
     var isMove:Boolean?=null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rbchek = sharedPref.getBoolean("rb", true)
        val lan = sharedPref.getString("language", "")

        isMove = false;
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


        bt_ar.setOnTouchListener{ _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->  cancelScaleAnimation(bt_ar)
                MotionEvent.ACTION_MOVE -> {
                    startScaleAnimation(bt_ar)
                    isMove = true
                }
                MotionEvent.ACTION_UP -> {
                    startScaleAnimation(bt_ar)
                    if (!isMove!!) {
                        isMove = false
                        val handler = Handler()
                        handler.postDelayed(Runnable {
                           nextFragment()

                        }, 200)
                    } else {
                        isMove = false
                    }
                }
            }

            changeLang("ar")
            bt_en.isSelected = false
            bt_ru.isSelected = false
            bt_ar.isSelected=true
            sharedPref.edit().putString("language", "ar").apply()

             true
        }


        bt_en.setOnTouchListener{ _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->  cancelScaleAnimation(bt_en)
                MotionEvent.ACTION_MOVE -> {
                    startScaleAnimation(bt_en)
                    isMove = true
                }
                MotionEvent.ACTION_UP -> {
                    startScaleAnimation(bt_en)
                    if (!isMove!!) {
                        isMove = false
                        val handler = Handler()
                        handler.postDelayed(Runnable {
                            nextFragment()
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                overridePendingTransition(
//                                    R.anim.enter_slide_left,
//                                    R.anim.exit_slide_left
//                                )
//                            }
                        }, 200)
                    } else {
                        isMove = false
                    }
                }
            }
            changeLang("en")
            bt_en.isSelected = true
            bt_ru.isSelected = false
            bt_ar.isSelected=false
            sharedPref.edit().putString("language", "en").apply()

            true
        }

        bt_ru.setOnTouchListener{ _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->  cancelScaleAnimation(bt_ru)
                MotionEvent.ACTION_MOVE -> {
                    startScaleAnimation(bt_ru)
                    isMove = true
                }
                MotionEvent.ACTION_UP -> {
                    startScaleAnimation(bt_ru)
                    if (!isMove!!) {
                        isMove = false
                        val handler = Handler()
                        handler.postDelayed(Runnable {
                            nextFragment()
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                overridePendingTransition(
//                                    R.anim.enter_slide_left,
//                                    R.anim.exit_slide_left
//                                )
//                            }
                        }, 200)
                    } else {
                        isMove = false
                    }
                }
            }

            changeLang("ru")
            bt_en.isSelected = false
            bt_ru.isSelected = true
            bt_ar.isSelected=false
            sharedPref.edit().putString("language", "ru").apply()

            true
        }


    }
    private fun startScaleAnimation(view: View) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 1.35f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1.35f)
        scaleDownX.duration = 200
        scaleDownY.duration = 200
        scaleDownX.start()
        scaleDownY.start()
    }
    private fun cancelScaleAnimation(view: View) {
        val scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f)
        scaleDownX.duration = 200
        scaleDownY.duration = 200
        scaleDownX.start()
        scaleDownY.start()
    }

   private fun nextFragment(){
      activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
           R.id.container2,
          PinPasFragment()
       )?.commit()
//       val intent = Intent(context, DashboardActivity::class.java)
//       intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//       startActivity(intent)
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