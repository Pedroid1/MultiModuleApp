package com.pedroid.data.repositories.tasks

import com.pedroid.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun insertTask(task: TaskEntity)
    fun deleteTask(task: TaskEntity)
    fun getTasks(): Flow<List<TaskEntity>>
    fun updateTask(task: TaskEntity)
}