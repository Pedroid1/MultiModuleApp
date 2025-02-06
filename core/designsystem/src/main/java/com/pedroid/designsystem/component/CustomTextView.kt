package com.pedroid.designsystem.component

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import com.google.android.material.color.MaterialColors
import com.pedroid.core.designsystem.R

class CustomTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val textTypeIndex =
            typedArray.getInt(R.styleable.CustomTextView_textType, EnumTextType.BODY1.ordinal)
        setTextType(EnumTextType.getTextType(textTypeIndex))
        setupTitleAccessibility(typedArray.getBoolean(R.styleable.CustomTextView_isTitle, false))
        setupPriorityText(
            EnumTextViewPriority.getTextPriority(
                typedArray.getInt(
                    R.styleable.CustomTextView_textPriority,
                    EnumTextViewPriority.SECONDARY.ordinal
                )
            )
        )
        typedArray.recycle()
        letterSpacing = 0.05f
    }

    private fun setupPriorityText(textPriority: EnumTextViewPriority) {
        val colorResId = when (textPriority) {
            EnumTextViewPriority.PRIMARY -> com.google.android.material.R.attr.colorOnBackground
            EnumTextViewPriority.SECONDARY -> null // Secondary is default text color
        }
        colorResId?.let { setTextColor(MaterialColors.getColor(this, colorResId)) }
    }

    private fun setupTitleAccessibility(isTitle: Boolean) {
        ViewCompat.setAccessibilityHeading(this, isTitle)
    }

    private fun setTextType(type: EnumTextType) {
        val fontResId = when (type) {
            EnumTextType.TITLE1, EnumTextType.TITLE2, EnumTextType.TITLE3 -> R.font.montserrat_semibold
            EnumTextType.SUBTITLE1, EnumTextType.SUBTITLE2 -> R.font.montserrat_medium
            else -> R.font.montserrat_regular
        }
        val textSizeSp = when (type) {
            EnumTextType.TITLE1 -> 20f
            EnumTextType.TITLE2 -> 17f
            EnumTextType.TITLE3 -> 14f
            EnumTextType.SUBTITLE1 -> 13f
            EnumTextType.SUBTITLE2 -> 11f
            EnumTextType.BODY1 -> 13f
            EnumTextType.BODY2 -> 12f
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
        BODY1,
        BODY2;

        companion object {
            @JvmStatic
            fun getTextType(index: Int): EnumTextType {
                return entries.toTypedArray().getOrElse(index) { BODY1 }
            }
        }
    }

    enum class EnumTextViewPriority {
        PRIMARY,
        SECONDARY;

        companion object {
            fun getTextPriority(index: Int): EnumTextViewPriority {
                return entries[index]
            }
        }
    }
}