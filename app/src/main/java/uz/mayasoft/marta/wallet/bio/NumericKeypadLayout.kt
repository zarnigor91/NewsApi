package uz.mayasoft.marta.wallet.bio

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import uz.mayasoft.marta.wallet.R
import uz.mayasoft.marta.wallet.ext.TextUtils
import java.math.BigDecimal

class NumericKeypadLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener, View.OnLongClickListener {

    private val colorDark: Int by lazy {
        ContextCompat.getColor(
            context,
            R.color.colorTransparent
        )
    }
    private val colorWhite: Int by lazy { ContextCompat.getColor(context, android.R.color.black) }
    private val dimension5: Float by lazy { context.resources.getDimension(R.dimen.view_numerickeypadlayout_child_text_size_small) }
    private val dimension10: Float by lazy { context.resources.getDimension(R.dimen.view_numerickeypadlayout_child_text_size) }
    private val dimension16: Float by lazy { context.resources.getDimension(R.dimen.view_numerickeypadlayout_child_padding) }

    private val selectableItemBackgroundId: Int by lazy {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        return@lazy outValue.resourceId
    }

    private var startLengthLimit: Int = 0
    private var maxLength: Int = Int.MAX_VALUE
    private var maxDecimalLength: Int = Int.MAX_VALUE
    private var value: String = ""

    private var listener: OnKeypadValueChangedListener? = null

    init {
        val tableLayout = TableLayout(context, attrs).apply {
            isMeasureWithLargestChildEnabled = true
            isShrinkAllColumns = true
            isStretchAllColumns = true
            layoutParams =
                TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            orientation = TableLayout.VERTICAL
            setBackgroundColor(Color.WHITE)

            val obtainStyledAttributes: TypedArray =
                getContext().obtainStyledAttributes(attrs, R.styleable.NumericKeypadLayout)

            maxLength =
                obtainStyledAttributes.getInteger(
                    R.styleable.NumericKeypadLayout_android_maxLength,
                    Int.MAX_VALUE
                )
            maxDecimalLength =
                obtainStyledAttributes.getInteger(
                    R.styleable.NumericKeypadLayout_maxDecimalLength,
                    Int.MAX_VALUE
                )
            val firstTextButtonString: String? =
                obtainStyledAttributes.getString(R.styleable.NumericKeypadLayout_firstTextButton)
            val secondTextButtonString: String? =
                obtainStyledAttributes.getString(R.styleable.NumericKeypadLayout_secondTextButton)
            val thirdTextButtonString: String? =
                obtainStyledAttributes.getString(R.styleable.NumericKeypadLayout_thirdTextButton)
            val fourthTextButtonString: String? =
                obtainStyledAttributes.getString(R.styleable.NumericKeypadLayout_fourthTextButton)

            obtainStyledAttributes.recycle()

            addView(
                getTableRowTextLine(
                    context,
                    attrs,
                    defStyleAttr,
                    "1",
                    "2",
                    "3",
                    firstTextButtonString
                )
            )
            addView(
                getTableRowTextLine(
                    context,
                    attrs,
                    defStyleAttr,
                    "4",
                    "5",
                    "6",
                    secondTextButtonString
                )
            )
            addView(
                getTableRowTextLine(
                    context,
                    attrs,
                    defStyleAttr,
                    "7",
                    "8",
                    "9",
                    thirdTextButtonString
                )
            )
            addView(
                getTableRowTextWithImageLine(
                    context,
                    attrs,
                    defStyleAttr,
                    TAG_IMAGE_DELETE,
                    R.drawable.core_presentation_drawable_backspace,
                    "",
                    "0",
                    fourthTextButtonString
                )
            )
        }

        addView(tableLayout)
    }

    fun clear() {
        value = ""; listener?.onKeypadValueChanged(value, false)
    }

    fun setOnKeypadValueChangedListener(listener: OnKeypadValueChangedListener) {
        this.listener = listener
    }

    fun setOnKeypadValueChangedListener(listener: (value: String, isCompleted: Boolean) -> Unit) {
        this.listener = object : OnKeypadValueChangedListener {
            override fun onKeypadValueChanged(value: String, isCompleted: Boolean) =
                listener.invoke(value, isCompleted)
        }
    }

    fun setValue(value: BigDecimal) = setValue(value.toString())

    fun setValue(value: String) {
        this.value = TextUtils.replaceAllLetters(value)
    }

    override fun onClick(view: View) {
        if (view is AppCompatTextView) {
            if (view.tag != TAG_TEXT_ADDITIONAL) {
                val isDecimalReachedMaxLength: Boolean = value.lastIndexOf(".").let {
                    if (it > -1) (value.length - (it - 1) - 1 == maxDecimalLength + 1)
                    else false
                }

                if (value.length <= maxLength && !isDecimalReachedMaxLength) {
                    val text: String = view.text.toString()
                    value += text
                }

                listener?.onKeypadValueChanged(value, value.length == maxLength + 1)

            }
        }

        if (view is AppCompatImageView && view.tag == TAG_IMAGE_DELETE) {
            if (value.isNotEmpty() && value.length != startLengthLimit) {
                value = value.substring(0, value.length - 1)
                listener?.onKeypadValueChanged(value, value.length == maxLength + 1)
            }
        }
    }

    override fun onLongClick(view: View): Boolean {
        if (view is AppCompatImageView && view.tag == TAG_IMAGE_DELETE) {
            value = value.substring(0, value.length - (value.length - startLengthLimit))
            listener?.onKeypadValueChanged("", false)
            return true
        }
        return false
    }

    private fun getTableRowTextLine(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
        vararg buttonValues: String?
    ): TableRow = TableRow(context, attrs).apply {
        addFrameLayout(
            getTextFrameLayout(
                context,
                attrs,
                defStyleAttr,
                colorDark,
                dimension10,
                null,
                buttonValues.getOrNull(0)
            )
        )
        addFrameLayout(
            getTextFrameLayout(
                context,
                attrs,
                defStyleAttr,
                colorDark,
                dimension10,
                null,
                buttonValues.getOrNull(1)
            )
        )
        addFrameLayout(
            getTextFrameLayout(
                context,
                attrs,
                defStyleAttr,
                colorDark,
                dimension10,
                null,
                buttonValues.getOrNull(2)
            )
        )
        buttonValues.getOrNull(3)?.let {
            addFrameLayout(
                getTextFrameLayout(
                    context, attrs, defStyleAttr, R.color.colorAccent, dimension5,
                    TAG_TEXT_ADDITIONAL, it
                )
            )
        }
    }

    @Suppress("SameParameterValue", "SameParameterValue")
    private fun getTableRowTextWithImageLine(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
        imageTag: Int?, drawableResId: Int, vararg buttonValues: String?
    ): TableRow = TableRow(context, attrs).apply {
        addFrameLayout(
            getTextFrameLayout(
                context,
                attrs,
                defStyleAttr,
                colorDark,
                dimension10,
                null,
                buttonValues.getOrNull(0)
            )
        )
        addFrameLayout(
            getTextFrameLayout(
                context,
                attrs,
                defStyleAttr,
                colorDark,
                dimension10,
                null,
                buttonValues.getOrNull(1)
            )
        )
        addFrameLayout(
            getImageFrameLayout(
                context,
                attrs,
                defStyleAttr,
                colorDark,
                imageTag,
                drawableResId
            )
        )
        buttonValues.getOrNull(2)?.let {
            addFrameLayout(
                getTextFrameLayout(
                    context,
                    attrs,
                    defStyleAttr,
                    R.color.colorAccent,
                    dimension5,
                    TAG_TEXT_ADDITIONAL,
                    it
                )
            )
        }
    }

    private fun getTextFrameLayout(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
        backgroundColor: Int, textSize: Float, textTag: Int?, text: String?
    ): FrameLayout {
        return FrameLayout(context, attrs, defStyleAttr).apply {
            setBackgroundColor(backgroundColor)
            AppCompatTextView(context, attrs, defStyleAttr)
                .apply {
                    dimension10.toInt().let { setPadding(it, it, it, it) }
                    ellipsize = android.text.TextUtils.TruncateAt.END
                    gravity = Gravity.CENTER
                    isSingleLine = true
                    tag = textTag
                    this.textSize = textSize
                    setBackgroundResource(selectableItemBackgroundId)
                    if (!text.isNullOrEmpty()) setOnClickListener(this@NumericKeypadLayout)
                    setTextColor(colorWhite)
                    setText(text)
                }
                .also { addView(it) }
        }
    }

    private fun getImageFrameLayout(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
        backgroundColor: Int, imageTag: Int?, drawableResId: Int
    ): FrameLayout {
        return FrameLayout(context, attrs, defStyleAttr).apply {
            setBackgroundColor(backgroundColor)
            AppCompatImageView(context, attrs, defStyleAttr)
                .apply {
                    val drawable: Drawable =
                        checkNotNull(ContextCompat.getDrawable(context, drawableResId))
                            .also { it.setColorFilter(colorWhite, PorterDuff.Mode.MULTIPLY) }
                    tag = imageTag
                    dimension16.toInt().let { setPadding(it, it, it, it) }
                    setBackgroundResource(selectableItemBackgroundId)
                    setImageDrawable(drawable)
                    setImageResource(drawableResId)
                    setOnClickListener(this@NumericKeypadLayout)
                    setOnLongClickListener(this@NumericKeypadLayout)
                }
                .also { addView(it) }
        }
    }

    private fun TableRow.addFrameLayout(layout: FrameLayout) {
        addView(layout)
        layout.layoutParams = TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1F)
            .also { params -> 1.let { params.setMargins(it, it, it, it) } }
    }

    interface OnKeypadValueChangedListener {
        fun onKeypadValueChanged(value: String, isCompleted: Boolean)
    }

    private companion object {
        const val TAG_IMAGE_DELETE: Int = 0
        const val TAG_TEXT_ADDITIONAL: Int = 1
    }
}
