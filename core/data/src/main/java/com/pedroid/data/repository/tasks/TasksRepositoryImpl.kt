package com.pedroid.data.repository.tasks

import com.pedroid.data.model.toEntity
import com.pedroid.database.BinDispatchers
import com.pedroid.database.Dispatcher
import com.pedroid.database.dao.TasksDao
import com.pedroid.database.model.toExternalModel
import com.pedroid.domain.repository.TasksRepository
import com.pedroid.model.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    @Dispatcher(BinDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val tasksDao: TasksDao
) : TasksRepository {
    override fun insertTask(task: Task) {
        CoroutineScope(ioDispatcher).launch {
            tasksDao.insertTask(task.toEntity())
        }
    }

    override fun deleteTask(task: Task) {
        CoroutineScope(ioDispatcher).launch {
            tasksDao.deleteTask(task.toEntity())
        }
    }

    override fun getTasks(): Flow<List<Task>> =
        tasksDao.getTaskEntities().map { it.map { it.toExternalModel() } }.flowOn(ioDispatcher)

    override fun updateTask(task: Task) {
        CoroutineScope(ioDispatcher).launch {
            tasksDao.insertTask(task.toEntity())
        }
    }
}