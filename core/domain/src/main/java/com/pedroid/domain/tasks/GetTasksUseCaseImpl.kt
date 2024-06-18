package com.pedroid.domain.tasks

import com.pedroid.data.repositories.tasks.TasksRepository
import com.pedroid.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTasksUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetTasksUseCase {
    override fun insertTask(task: Task) {
        return repository.insertTask(task)
    }

    override fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }

    override fun updateTask(task: Task) {
        repository.updateTask(task)
    }

    override fun getTasks(): Flow<List<Task>> = repository.getTasks().map { list ->
        list.sortedWith(compareBy<Task> {
            it.isChecked
        }.thenBy {
            it.title.lowercase()
        })
    }
}