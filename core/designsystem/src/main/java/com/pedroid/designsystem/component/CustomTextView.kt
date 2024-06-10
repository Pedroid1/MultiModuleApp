package com.pedroid.designsystem.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import com.pedroid.core.designsystem.R

class CustomTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val textTypeIndex = typedArray.getInt(R.styleable.CustomTextView_textType, EnumTextType.BODY1.ordinal)
        setTextType(EnumTextType.getTextType(textTypeIndex))
        setupTitleAccessibility(typedArray.getBoolean(R.styleable.CustomTextView_isTitle, false))
        typedArray.recycle()
        letterSpacing = 0.05f
    }

    private fun setupTitleAccessibility(isTitle: Boolean) {
        ViewCompat.setAccessibilityHeading(this, isTitle)
    }

    private fun setTextType(type: EnumTextType) {
        val fontResId = when (type) {
            EnumTextType.TITLE1, EnumTextType.TITLE2, EnumTextType.TITLE3 -> R.font.poppins_semibold
            EnumTextType.SUBTITLE1, EnumTextType.SUBTITLE2, EnumTextType.HEADER -> R.font.poppins_medium
            else -> R.font.poppins_regular
        }
        val textSizeSp = when (type) {
            EnumTextType.TITLE1 -> 20f
            EnumTextType.TITLE2 -> 17f
            EnumTextType.TITLE3 -> 14f
            EnumTextType.SUBTITLE1 -> 13f
            EnumTextType.SUBTITLE2 -> 11f
            EnumTextType.HEADER, EnumTextType.BODY1 -> 13f
            EnumTextType.BODY2 -> 12f
            EnumTextType.CAPTION -> 10f
            EnumTextType.OVERLINE -> {
                isAllCaps = true
                11f
            }
        }
        val font = ResourcesCompat.getFont(context, fontResId)
        this.typeface = font
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp)
    }

    enum class EnumTextType {
        TITLE1,
        TITLE2,
        TITLE3,
        SUBTITLE1,
        SUBTITLE2,
        HEADER,
        BODY1,
        BODY2,
        CAPTION,
        OVERLINE;

        companion object {
            @JvmStatic
            fun getTextType(index: Int): EnumTextType {
                return entries.toTypedArray().getOrElse(index) { BODY1 }
            }
        }
    }
}