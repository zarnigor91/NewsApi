package uz.mayasoft.marta.wallet.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.dialog.AlertDialogs
import uz.mayasoft.marta.wallet.model.AcsessTokenResponce
import uz.mayasoft.marta.wallet.model.LoginResponce
import uz.mayasoft.marta.wallet.network.PostApi
import uz.mayasoft.marta.wallet.utils.PreferencesUtil
import uz.mayasoft.marta.wallet.vm.TokenViewModel
import javax.inject.Inject


class DashboardActivity :AppCompatActivity(){

    private var acsess: AcsessTokenResponce?=null
    private var acsessToken: LoginResponce?=null
    @Inject
    lateinit var tokenPreferencesUtil: PreferencesUtil

    @Inject
    lateinit var api: PostApi
     lateinit var tokenViewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val fragment = DashboardFragment()
        if(savedInstanceState==null){
            addFragment(fragment)
        }
        AlertDialogs.closeProgress()
    }



    override fun onRestart() {
        super.onRestart()

    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.enter_left,
            R.anim.exit_left,
            R.anim.enter_right,
            R.anim.exit_right
        ).addToBackStack(null)
            .replace(R.id.container, fragment, fragment.javaClass.simpleName)
            .commit()
    }



}