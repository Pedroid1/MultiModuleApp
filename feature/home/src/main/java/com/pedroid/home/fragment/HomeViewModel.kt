package com.pedroid.home.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pedroid.common.data.asResult
import com.pedroid.common.base.BaseState
import com.pedroid.common.base.BaseViewModel
import com.pedroid.common.data.resultResource
import com.pedroid.common.util.Event
import com.pedroid.domain.ValidationResult
import com.pedroid.domain.model.Task
import com.pedroid.domain.tasks.GetTasksUseCase
import com.pedroid.domain.ui.task.TaskValidationUseCase
import com.pedroid.home.fragment.adapter.TaskListAdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetTasksUseCase,
    private val validationUseCase: TaskValidationUseCase
) : BaseViewModel() {

    private val _homeUiState = MutableLiveData(BaseState<List<Task>>())
    val homeUiState: LiveData<BaseState<List<Task>>> = _homeUiState

    private val _validationLiveData = MutableLiveData<Event<ValidationResult>>()
    val validationLiveData: LiveData<Event<ValidationResult>> = _validationLiveData

    private val _insertResultLiveData = MutableLiveData<Event<Boolean>>()
    val insertResultLiveData: LiveData<Event<Boolean>> = _insertResultLiveData

    init {
        useCase.getTasks().asResult().onEach { result ->
            resultResource(result, _homeUiState)
        }.launchIn(viewModelScope)
    }

    fun insertTask(title: String, description: String) {
        val result = validationUseCase.validateTitle(title)
        if (result.successful) {
            useCase.insertTask(Task(title = title, description = description))
            _insertResultLiveData.value = Event(true)
        } else {
            _validationLiveData.value = Event(result)
        }
    }

    fun insertTask(task: Task) {
        useCase.insertTask(task)
    }

    fun deleteTask(task: Task) {
        useCase.deleteTask(task)
    }

    fun generateHomeAdapterList(list: List<Task>): List<TaskListAdapterItem> {
        val array: MutableList<TaskListAdapterItem> = mutableListOf()
        if (list.isEmpty()) {
            array.add(TaskListAdapterItem.EmptyItem())
        } else {
            list.forEach { array.add(TaskListAdapterItem.TaskItem(it)) }
        }
        return array
    }

}