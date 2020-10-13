package uz.mayasoft.marta.wallet.ui.dashboard

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class PaymeModel(
    val image: Drawable?=null,
    val name: String? = ""
)