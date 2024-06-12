package com.pedroid.home.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pedroid.common.asResult
import com.pedroid.common.base.BaseState
import com.pedroid.common.base.BaseViewModel
import com.pedroid.common.resultResource
import com.pedroid.domain.model.Task
import com.pedroid.domain.tasks.GetTasksUseCase
import com.pedroid.home.fragment.adapter.TaskListAdapter
import com.pedroid.home.fragment.adapter.TaskListAdapterItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetTasksUseCase
) : BaseViewModel() {

    private val _homeUiState = MutableLiveData(BaseState<List<Task>>())
    val homeUiState get() = _homeUiState

    init {
        useCase.getTasks().asResult().onEach { result ->
            resultResource(result, _homeUiState)
        }.launchIn(viewModelScope)
    }

    fun insertTask(task: Task) {
        useCase.insertTask(task)
    }

    fun deleteTask(task: Task) {
        useCase.insertTask(task)
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