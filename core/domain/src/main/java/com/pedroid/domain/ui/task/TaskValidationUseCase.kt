package com.pedroid.domain.ui.task

import com.pedroid.domain.ValidationResult

interface TaskValidationUseCase {
    fun validateTitle(title: String): ValidationResult
}