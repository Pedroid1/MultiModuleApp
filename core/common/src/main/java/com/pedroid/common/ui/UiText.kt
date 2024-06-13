package com.pedroid.common.ui

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val message: String) : UiText()
    class StringResource(@StringRes val resId: Int, vararg val args: Any) : UiText()

    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> message
            is StringResource -> context.getString(resId, *args)
        }
    }
}