package com.pedroid.domain.tasks

import com.pedroid.data.repositories.tasks.TasksRepository
import com.pedroid.domain.model.Task
import com.pedroid.domain.model.toEntity
import com.pedroid.domain.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTasksUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetTasksUseCase {
    override fun insertTask(task: Task) {
        return repository.insertTask(task.toEntity())
    }

    override fun deleteTask(task: Task) {
        repository.deleteTask(task.toEntity())
    }

    override fun getTasks(): Flow<List<Task>> = repository.getTasks().map { list ->
        list.sortedBy { it.isChecked }.map { it.toModel() }
    }
}