package com.example.marta.bio

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.marta.R
import kotlinx.android.synthetic.main.verification_fragment.*
import org.jetbrains.annotations.NotNull


class VerificationFragment :Fragment(R.layout.verification_fragment){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          im_finger.setOnClickListener {
              fragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(
                  R.id.container2,
                 yan()
              )?.commit()
          }
    }
}