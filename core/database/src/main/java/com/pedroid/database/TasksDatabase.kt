package com.pedroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedroid.database.dao.TasksDao
import com.pedroid.database.model.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}