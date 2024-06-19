package com.pedroid.testing.repository

import com.pedroid.domain.repository.TasksRepository
import com.pedroid.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestTasksRepository: TasksRepository {

    private val tasksFlow = MutableStateFlow(emptyList<Task>())

    override fun insertTask(task: Task) = tasksFlow.update { oldValues ->
        oldValues.filterNot { it.uid == task.uid } + task
    }

    override fun deleteTask(task: Task) = tasksFlow.update { oldValues ->
        oldValues - task
    }

    override fun getTasks(): Flow<List<Task>> = tasksFlow

    override fun updateTask(task: Task) = insertTask(task)

}