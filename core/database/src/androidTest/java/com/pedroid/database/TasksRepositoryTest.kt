package com.pedroid.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.pedroid.database.dao.TasksDao
import com.pedroid.database.model.TaskEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class TasksRepositoryTest {

    lateinit var database: TasksDatabase
    private lateinit var dao: TasksDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            TasksDatabase::class.java,
        ).build()
        dao = database.tasksDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTask_shouldReturnInsertedTask() = runTest {
        val task = TaskEntity(uid = 1, title = "", description = "")
        dao.insertTask(task)
        val tasks = dao.getTaskEntities().first()
        assertThat(tasks.first()).isEqualTo(task)
    }

    @Test
    fun deleteTask_shouldReturnEmptyList() = runTest {
        val task = TaskEntity(uid = 1, title = "", description = "")
        dao.insertTask(task)
        dao.deleteTask(task)
        val tasks = dao.getTaskEntities().first()
        assertThat(tasks).isEmpty()
    }

    @Test
    fun getTasks_shouldReturnEmptyListWhenNoTasksInserted() = runTest {
        val tasks = dao.getTaskEntities().first()
        assertThat(tasks).isEmpty()
    }
}