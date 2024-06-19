package com.pedroid.home.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.pedroid.common.util.handleOpt
import com.pedroid.domain.ValidationResult
import com.pedroid.domain.tasks.GetTasksUseCaseImpl
import com.pedroid.domain.ui.task.TaskValidationUseCase
import com.pedroid.home.fragment.adapter.TaskListAdapterItem
import com.pedroid.model.Task
import com.pedroid.testing.repository.TestTasksRepository
import com.pedroid.testing.util.MainDispatcherRule
import getOrAwaitValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
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
    fun `when insertTask is called, then homeUiState should contain the inserted task`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.homeState.collect()
        }

        val task = Task(title = "", description = "")
        viewModel.insertTask(task)

        assertThat(viewModel.homeState.value.data?.handleOpt()).contains(task)
    }


    @Test
    fun `when deleteTask is called, then homeUiState should not contain the deleted task`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.homeState.collect()
        }

        val task = Task(title = "", description = "")
        viewModel.insertTask(task)

        assertThat(viewModel.homeState.value.data?.handleOpt()).contains(task)

        viewModel.deleteTask(task)

        assertThat(viewModel.homeState.value.data?.handleOpt()).doesNotContain(task)
    }

    @Test
    fun `when updateTask is called, then homeUiState should contain updated task`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.homeState.collect()
        }

        val task = Task(uid = 1, title = "", description = "")
        viewModel.insertTask(task)

        assertThat(viewModel.homeState.value.data?.handleOpt()).contains(task)

        val updatedTask = task.copy(title = "Updated")
        viewModel.updateTask(updatedTask)

        assertThat(viewModel.homeState.value.data?.handleOpt()).doesNotContain(task)
        assertThat(viewModel.homeState.value.data?.handleOpt()).contains(updatedTask)
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

    @Test
    fun `when generateHomeAdapterList called with empty list, return list with EmptyItem`() {
        val list = viewModel.generateHomeAdapterList(emptyList())
        assertThat(list[0]).isInstanceOf(TaskListAdapterItem.EmptyItem::class.java)
    }

    @Test
    fun `when generateHomeAdapterList called with taskList, return list with TaskItems`() {
        val list = listOf(Task(title = "", description = ""))
        val listAdapter = viewModel.generateHomeAdapterList(list)
        assertThat(listAdapter[0]).isInstanceOf(TaskListAdapterItem.TaskItem::class.java)
    }
}