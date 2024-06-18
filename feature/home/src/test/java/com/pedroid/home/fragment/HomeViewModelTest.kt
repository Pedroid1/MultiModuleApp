package com.pedroid.home.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.pedroid.common.util.handleOpt
import com.pedroid.domain.ValidationResult
import com.pedroid.domain.tasks.GetTasksUseCaseImpl
import com.pedroid.domain.ui.task.TaskValidationUseCase
import com.pedroid.model.Task
import com.pedroid.testing.repository.TestTasksRepository
import com.pedroid.testing.util.MainDispatcherRule
import getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockTaskUseCase = spyk(GetTasksUseCaseImpl(TestTasksRepository()))
    private val mockValidationUseCase: TaskValidationUseCase = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(mockTaskUseCase, mockValidationUseCase)
    }

    @Test
    fun `given the initial state of ViewModel, when ViewModel is instantiated, then validationLiveData should be null`() {
        assertThat(viewModel.validationLiveData.value).isNull()
    }

    @Test
    fun `given the initial state of ViewModel, when ViewModel is instantiated, then insertResultLiveData should be null`() {
        assertThat(viewModel.insertResultLiveData.value).isNull()
    }

    @Test
    fun `when ViewModel is instantiated, then GetTasksUseCase should be called`() {
        viewModel.run {
            verify { mockTaskUseCase.getTasks() }
        }
    }

    @Test
    fun `when insertTask is called, then homeUiState should contain the inserted task`() {
        val task = Task(title = "", description = "")
        viewModel.insertTask(task)

        assertThat(viewModel.homeUiState.getOrAwaitValue().data.handleOpt()).contains(task)
    }

    @Test
    fun `when deleteTask is called, then homeUiState should not contain the deleted task`() {
        val task = Task(title = "", description = "")
        viewModel.insertTask(task)
        assertThat(viewModel.homeUiState.getOrAwaitValue().data.handleOpt()).contains(task)

        viewModel.deleteTask(task)
        assertThat(viewModel.homeUiState.getOrAwaitValue().data.handleOpt()).doesNotContain(task)
    }

    @Test
    fun `when add task with invalid title, then validationLiveData should receive invalid validation`() {
        val task = Task(title = "", description = "")
        every { mockValidationUseCase.validateTitle(any()) } returns ValidationResult(false)
        viewModel.insertTaskWithFieldsValidation(task)
        assertThat(viewModel.validationLiveData.getOrAwaitValue().getContentIfNotHandled()?.successful.handleOpt()).isFalse()
    }

    @Test
    fun `when add task with valid title, then insertResultLiveData should receive true`() {
        val task = Task(title = "Test", description = "")
        every { mockValidationUseCase.validateTitle(any()) } returns ValidationResult(true)
        viewModel.insertTaskWithFieldsValidation(task)
        assertThat(viewModel.insertResultLiveData.getOrAwaitValue().getContentIfNotHandled().handleOpt()).isTrue()
    }
}