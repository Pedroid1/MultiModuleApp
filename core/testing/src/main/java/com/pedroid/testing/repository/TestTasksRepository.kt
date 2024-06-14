package com.pedroid.testing.repository

import com.pedroid.data.model.TaskEntity
import com.pedroid.data.repositories.tasks.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class TestTasksRepository: TasksRepository {

    private val tasksFlow = MutableStateFlow(emptyList<TaskEntity>())

    override fun insertTask(task: TaskEntity) = tasksFlow.update { oldValues ->
        oldValues + task
    }

    override fun deleteTask(task: TaskEntity) = tasksFlow.update { oldValues ->
        oldValues - task
    }

    override fun getTasks(): Flow<List<TaskEntity>> = tasksFlow

    override fun updateTask(task: TaskEntity) = insertTask(task)

}