package com.example.marta.bio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.marta.R
import kotlinx.android.synthetic.main.verification_fragment.*

class PinCodeCreationFragment : Fragment(R.layout.verification_fragment) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            im_finger.setOnClickListener {
              //fragment to
            }

    }
}