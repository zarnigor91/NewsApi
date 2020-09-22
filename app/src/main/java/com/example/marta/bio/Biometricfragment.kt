package com.example.marta.bio

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.example.marta.R
import java.util.concurrent.Executor

class Biometricfragment:DialogFragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTransparent()
    }

    override fun onResume() {
        super.onResume()
        setTransparent()
        checkBiometricAvailability()
    }

    private fun checkBiometricAvailability() {
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate()) {

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                showErrorDialog(R.string.feture)
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                showErrorDialog(R.string.feture)
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                showErrorDialog(R.string.feture)
            }
            BiometricManager.BIOMETRIC_SUCCESS -> {
                instantiateBiometric()
            }
        }
    }

    private fun instantiateBiometric() {
        val executor: Executor = ContextCompat.getMainExecutor(requireContext())
        val biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                  Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationFailed() {
                    Toast.makeText(context,"boshqa",Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show()
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("_use_finger_print")
            .setNegativeButtonText("core_presentation_common_cancel")
            .build()
        biometricPrompt.authenticate(promptInfo)
    }

    private fun setTransparent() {
        if (dialog != null) {
            dialog?.window?.apply {
                setBackgroundDrawableResource(android.R.color.transparent)
                clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                attributes?.dimAmount = 0.0f
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showErrorDialog(messageId: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("error")
            .setMessage(messageId)
            .setPositiveButton("ok") { _, _ -> dismiss() }
            .setCancelable(false)
            .create()
            .show()
    }


}