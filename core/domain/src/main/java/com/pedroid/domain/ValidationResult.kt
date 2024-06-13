package com.pedroid.domain

import com.pedroid.common.ui.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)
