package com.pedroid.domain.repository

import com.pedroid.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun insertTask(task: Task)
    fun deleteTask(task: Task)
    fun getTasks(): Flow<List<Task>>
    fun updateTask(task: Task)
}