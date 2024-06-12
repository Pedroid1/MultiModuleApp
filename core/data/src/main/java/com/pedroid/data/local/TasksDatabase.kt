package com.pedroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedroid.data.dao.TasksDao
import com.pedroid.data.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}