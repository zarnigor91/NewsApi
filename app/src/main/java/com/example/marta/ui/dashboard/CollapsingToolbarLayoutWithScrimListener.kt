package com.example.marta.ui.dashboard

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.CollapsingToolbarLayout


class CollapsingToolbarLayoutWithScrimListener : CollapsingToolbarLayout {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
    }

    private var scrimListener: ScrimListener? = null
    override fun setScrimsShown(
        shown: Boolean,
        animate: Boolean
    ) {
        super.setScrimsShown(shown, animate)
        if (scrimListener != null) scrimListener!!.onScrimShown(shown)
    }

    fun setScrimListener(listener: ScrimListener?) {
        scrimListener = listener
    }

    interface ScrimListener {
        fun onScrimShown(shown: Boolean)
    }
}