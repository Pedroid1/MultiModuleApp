package com.pedroid.domain.tasks

import com.pedroid.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface GetTasksUseCase {
    fun insertTask(task: Task)
    fun deleteTask(task: Task)
    fun getTasks(): Flow<List<Task>>
}