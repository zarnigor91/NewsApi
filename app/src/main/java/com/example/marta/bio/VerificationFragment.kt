package com.example.marta.bio

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.marta.R
import kotlinx.android.synthetic.main.verification_fragment.*


class VerificationFragment :Fragment(R.layout.verification_fragment){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        num_pad.setOnKeypadValueChangedListener { value, _ ->
            pin_code_view.apply {
                requestFocus()
                setText(value)
            }
            if (value.length == 4) {
Toast.makeText(requireContext(),"Terildi $value",Toast.LENGTH_LONG).show()
            }
        }
          im_finger.setOnClickListener {
              fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
                  R.id.container2,
                 Biometricfragment()
              )?.commit()
          }

}}