package com.example.marta.ui.dashboard.qr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.marta.R
import com.google.zxing.integration.android.IntentIntegrator

class QrActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_cod_layout)

        letsScan()

        findViewById<Button>(R.id.btn).setOnClickListener { letsScan() }
    }

    private fun letsScan() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("scan")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        scanResult.let {
            findViewById<TextView>(R.id.value_text).text = scanResult.contents
        }
    }
}