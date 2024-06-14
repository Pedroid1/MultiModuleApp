package com.pedroid.domain.ui.task

import com.google.common.truth.Truth.assertThat
import com.pedroid.common.util.handleOpt
import org.junit.Test

class TaskValidationUseCaseTest {

    private val useCase = TaskValidationUseCaseImpl()

    @Test
    fun `Give empty title return invalid validation`() {
        val validation = useCase.validateTitle("")
        assertThat(validation.successful.handleOpt()).isFalse()
    }

    @Test
    fun `Give valid title return valid validation`() {
        val validation = useCase.validateTitle("Test")
        assertThat(validation.successful.handleOpt()).isTrue()
    }
}