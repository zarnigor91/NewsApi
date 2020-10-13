package uz.mayasoft.marta.wallet.pin2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.pin2.security.PFResult
import uz.mayasoft.marta.wallet.pin2.viewmodels.PFPinCodeViewModel
import uz.mayasoft.marta.wallet.ui.dashboard.DashboardActivity

class PinPasFragment:Fragment(R.layout.activity_main){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLockScreenFragment()
    }

    private fun showLockScreenFragment() {
        PFPinCodeViewModel()
            .isPinCodeEncryptionKeyExist.observe(
                viewLifecycleOwner,
            Observer<PFResult<Boolean>> { result ->
                if (result == null) {
                    return@Observer
                }
                if (result.getError() != null) {
                    Toast.makeText(
                        requireContext(),
                        "Can not get pin code info",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Observer
                }
                showLockScreenFragment(result.result)
            }
        )
    }
    private fun showLockScreenFragment(isPinExist: Boolean) {
        val builder: PFFLockScreenConfiguration.Builder = PFFLockScreenConfiguration.Builder(
            requireContext()
        )
            .setTitle(if (isPinExist) "Unlock with your pin code or fingerprint" else "Create Code")
            .setCodeLength(6)
            .setLeftButton("Can't remeber")
            .setNewCodeValidation(true)
            .setNewCodeValidationTitle("Please input code again")
            .setUseFingerprint(true)
        val fragment = PinFragment()
        fragment.setOnLeftButtonClickListener(View.OnClickListener {
            Toast.makeText(
//                this@PinFragment,
                requireContext(),
                "Left button pressed",
                Toast.LENGTH_LONG
            ).show()
        })
        builder.setMode(if (isPinExist) PFFLockScreenConfiguration.MODE_AUTH else PFFLockScreenConfiguration.MODE_CREATE)
        if (isPinExist) {
            fragment.setEncodedPinCode(PreferencesSettings.getCode(requireContext())!!)
            fragment.setLoginListener(mLoginListener)
        }
        fragment.setConfiguration(builder.build())
        fragment.setCodeCreateListener(mCodeCreateListener)
       activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container2, fragment)?.commit()
    }
    private val mCodeCreateListener: PinFragment.OnPFLockScreenCodeCreateListener =
        object : PinFragment.OnPFLockScreenCodeCreateListener {
            override fun onCodeCreated(encodedCode: String?) {
                Toast.makeText(requireContext(), "Code created", Toast.LENGTH_SHORT).show()
                       val intent = Intent(context, DashboardActivity::class.java)
       intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
       startActivity(intent)
                PreferencesSettings.saveToPref(requireContext(), encodedCode)
            }

            override fun onNewCodeValidationFailed() {
                Toast.makeText(requireContext(), "Code validation error", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    private val mLoginListener: PinFragment.OnPFLockScreenLoginListener =
        object : PinFragment.OnPFLockScreenLoginListener {
            override fun onCodeInputSuccessful() {
                Toast.makeText(requireContext(), "Code successfull", Toast.LENGTH_SHORT).show()
                showMainFragment()
                val intent = Intent(context, DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

            override fun onFingerprintSuccessful() {
                Toast.makeText(requireContext(), "Fingerprint successfull", Toast.LENGTH_SHORT)
                    .show()
                showMainFragment()
            }

            override fun onPinLoginFailed() {
                Toast.makeText(requireContext(), "Pin failed", Toast.LENGTH_SHORT).show()
            }

            override fun onFingerprintLoginFailed() {
                Toast.makeText(requireContext(), "Fingerprint failed", Toast.LENGTH_SHORT).show()
            }
        }
    private fun showMainFragment() {
//        val fragment = MainFragment()
//        getSupportFragmentManager().beginTransaction()
//            .replace(R.id.container_view, fragment).commit()
    }

}