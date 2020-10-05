//package com.example.marta.dialog
//
//
//import android.app.Activity
//
//import android.app.Dialog
//import android.content.Context
//import android.content.DialogInterface
//import android.view.LayoutInflater
//import android.view.View
//import android.view.WindowManager
//import android.widget.TextView
//import androidx.appcompat.app.AlertDialog
//import com.example.marta.R
//import com.readystatesoftware.chuck.internal.ui.MainActivity
//import org.apache.commons.lang3.StringUtils;
//
//
//object AlertDialogs {
//    fun showMessage(context: Context?, message: String?) {
//        AlertDialog.Builder(context!!, R.style.AlertDialog)
//            .setTitle(R.string.app_name)
//            .setMessage(message)
//            .create()
//            .show()
//    }
//
//    fun progressDialog(
//        context: Context?,
//        message: String?
//    ): Dialog {
//        val dialog: Dialog
//        val builder =
//            AlertDialog.Builder(context!!, R.style.AppTheme_NoActionBar)
//        val view: View =
//            LayoutInflater.from(context).inflate(R.layout.layout_progress_bar, null)
//        (view.findViewById<View>(R.id.loading_msg) as TextView).text = message
//        builder.setView(view)
//        dialog = builder.create()
//        dialog.getWindow()!!.attributes.windowAnimations = R.style.DialogTheme
//        dialog.getWindow()!!.setBackgroundDrawableResource(R.color.colorTransparent)
//        dialog.getWindow()!!.setLayout(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT
//            )
//        dialog.show()
//        if (context is MainActivity) {
//            dialog.setCanceledOnTouchOutside(false)
//            dialog.setCancelable(false)
//        }
//        return dialog
//    }
//
//    fun showCloseMessage(
//        context: Context?,
//        positiveListener: DialogInterface.OnClickListener?,
//        negativeListener: DialogInterface.OnClickListener?
//    ) {
//        AlertDialog.Builder(context!!, R.style.AlertDialog)
//            .setCancelable(true)
//            .setMessage(R.string.txt_msg_close)
//            .setPositiveButton(R.string.action_close, positiveListener)
//            .setNegativeButton(R.string.action_cancel, negativeListener)
//            .create()
//            .show()
//    }
//
//    fun showConfirmationMessage(
//        context: Context?,
//        message: String?,
//        positiveListener: DialogInterface.OnClickListener?
//    ) {
//        AlertDialog.Builder(context!!, R.style.AlertDialog)
//            .setCancelable(false)
//            .setMessage(message)
//            .setPositiveButton(R.string.back, positiveListener)
//            .create()
//            .show()
//    }
//
//    fun showPermissionMessage(
//        activity: Activity,
//        positiveListener: DialogInterface.OnClickListener?, vararg permissions: String?
//    ) {
//        val permText: String = StringUtils.join(permissions, '\n')
//        val alertDialog: AlertDialog =
//            AlertDialog.Builder(activity, R.style.AlertDialog)
//                .setCancelable(true)
//                .setTitle(R.string.permission_is_needed)
//                .setMessage(permText)
//                .setPositiveButton(R.string.approve, positiveListener)
//                .setNegativeButton(R.string.action_cancel, { dialog, which -> activity.finish() })
//                .create()
//        alertDialog.setCanceledOnTouchOutside(false)
//        alertDialog.show()
//    }
//}