package com.example.marta.ui.pin.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import com.example.marta.R
import java.util.*


/**
 * Created by Aleksandr Nikiforov on 2018/02/07.
 */
class PFCodeView : LinearLayout {
    var mCodeViews: MutableList<CheckBox> = ArrayList()
    var code = ""
        private set
    private var mCodeLength = DEFAULT_CODE_LENGTH
    private var mListener:OnPFCodeListener? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.view_code_pf_lockscreen, this)
        setUpCodeViews()
    }

    fun setCodeLength(codeLength: Int) {
        mCodeLength = codeLength
        setUpCodeViews()
    }

    private fun setUpCodeViews() {
        removeAllViews()
        mCodeViews.clear()
        code = ""
        for (i in 0 until mCodeLength) {
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.view_pf_code_checkbox, null) as CheckBox
            val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val margin = resources.getDimensionPixelSize(R.dimen.code_fp_margin)
            layoutParams.setMargins(margin, margin, margin, margin)
            view.layoutParams = layoutParams
            view.isChecked = false
            addView(view)
            mCodeViews.add(view)
        }
        mListener?.onCodeNotCompleted("")
    }

    fun input(number: String): Int {
        if (code.length == mCodeLength) {
            return code.length
        }
        mCodeViews[code.length].toggle() //.setChecked(true);
        code += number
        if (code.length == mCodeLength && mListener != null) {
            mListener!!.onCodeCompleted(code)
        }
        return code.length
    }

    fun delete(): Int {
        mListener?.onCodeNotCompleted(code)
        if (code.length == 0) {
            return code.length
        }
        code = code.substring(0, code.length - 1)
        mCodeViews[code.length].toggle() //.setChecked(false);
        return code.length
    }

    fun clearCode() {
        mListener?.onCodeNotCompleted(code)
        code = ""
        for (codeView in mCodeViews) {
            codeView.isChecked = false
        }
    }

    val inputCodeLength: Int
        get() = code.length

    fun setListener(listener:OnPFCodeListener?) {
        mListener = listener
    }

    interface OnPFCodeListener {
        fun onCodeCompleted(code: String?)
        fun onCodeNotCompleted(code: String?)
    }

    companion object {
        private const val DEFAULT_CODE_LENGTH = 4
    }
}