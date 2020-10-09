//package com.example.marta.ui
//
//import android.app.AlertDialog
//import android.graphics.Color
//import android.graphics.drawable.ColorDrawable
//import android.util.DisplayMetrics
//import android.view.LayoutInflater
//import android.view.WindowManager
//import androidx.fragment.app.Fragment
//import com.example.marta.R
//
//open class BaseFragment(layoutId:Int): Fragment(layoutId),BaseView {
//
//
//    private var progressDialog: AlertDialog? = null
//
//    override fun showProgressDialog() {
//        val progressView =
//            LayoutInflater.from(activity).inflate(R.layout.view_progress_dialog, null)
//        val progressBuilder = AlertDialog.Builder(activity)
//            .setView(progressView)
//            .setCancelable(false)
//        progressDialog = progressBuilder.show()
//        progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        val displayMetrics = DisplayMetrics()
//        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
//        val displayWidth = displayMetrics.widthPixels
//        val displayHeight = displayMetrics.heightPixels
//
//        val layoutParams = WindowManager.LayoutParams()
//        layoutParams.copyFrom(progressDialog?.window?.attributes)
//        val dialogWindowWidth = (displayWidth * 0.5f).toInt()
//        val dialogWindowHeight = (displayHeight * 0.2f).toInt()
//        layoutParams.width = dialogWindowWidth
//        layoutParams.height = dialogWindowHeight
//        progressDialog?.window?.attributes = layoutParams
//    }
//
//    override fun hideProgressDialog() {
//        progressDialog?.dismiss()
//    }
//
//}