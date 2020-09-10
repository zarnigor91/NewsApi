//package com.example.marta.bio
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import com.example.marta.R
//import kotlinx.android.synthetic.main.verification_fragment.*
//
//class PinCodeCreationFragment : Fragment(R.layout.fragment_feature_pincode_creation) {
//
//    private val viewModel by lifecycleScope.viewModel<PinCodeCreationViewModel>(this)
//    private val binding: ViewBinding by viewBinding()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        onBackPressedDispatcher.addCallback(this) { viewModel.backToAuthScreen() }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.apply {
//            numericKeypadLayout.setOnKeypadValueChangedListener { value, _ ->
//                pinCodeView.apply {
//                    requestFocus()
//                    setText(value)
//                }
//                if (value.length == 4) {
//                    viewModel.openPinCodeConfirmationScreen(value)
//                }
//            }
//            im_finger.setOnClickListener {
//              //fragment to
//            }
//        }
//    }
//}