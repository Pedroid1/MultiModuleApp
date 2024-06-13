package com.pedroid.domain.ui.task

import com.pedroid.common.ui.UiText
import com.pedroid.domain.R
import com.pedroid.domain.ValidationResult
import javax.inject.Inject

class TaskValidationUseCaseImpl @Inject constructor() : TaskValidationUseCase {

    override fun validateTitle(title: String): ValidationResult {
        if (title.isEmpty()) {
            return ValidationResult(
                false, UiText.StringResource(R.string.title_cant_be_empty)
            )
        }
        return ValidationResult(
            successful = true
        )
    }

}