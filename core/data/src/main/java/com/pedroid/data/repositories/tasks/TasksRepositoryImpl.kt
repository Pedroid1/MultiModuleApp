package com.pedroid.data.repositories.tasks

import com.pedroid.data.BinDispatchers
import com.pedroid.data.Dispatcher
import com.pedroid.data.dao.TasksDao
import com.pedroid.data.model.TaskEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    @Dispatcher(BinDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val tasksDao: TasksDao
) : TasksRepository {
    override fun insertTask(task: TaskEntity) {
        CoroutineScope(ioDispatcher).launch {
            tasksDao.insertTask(task)
        }
    }

    override fun deleteTask(task: TaskEntity) {
        CoroutineScope(ioDispatcher).launch {
            tasksDao.deleteTask(task)
        }
    }

    override fun getTasks(): Flow<List<TaskEntity>> =
        tasksDao.getTaskEntities().flowOn(ioDispatcher)
}