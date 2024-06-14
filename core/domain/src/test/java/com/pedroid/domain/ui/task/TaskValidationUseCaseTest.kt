package com.pedroid.domain.ui.task

import com.google.common.truth.Truth.assertThat
import com.pedroid.common.util.handleOpt
import org.junit.Test

class TaskValidationUseCaseTest {

    private val useCase = TaskValidationUseCaseImpl()

    @Test
    fun `given empty title when validateTitle is called then return invalid validation`() {
        val validation = useCase.validateTitle("")
        assertThat(validation.successful.handleOpt()).isFalse()
    }

    @Test
    fun `given valid title when validateTitle is called then return valid validation`() {
        val validation = useCase.validateTitle("Test")
        assertThat(validation.successful.handleOpt()).isTrue()
    }
}