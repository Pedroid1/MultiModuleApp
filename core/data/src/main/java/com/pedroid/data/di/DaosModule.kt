package com.pedroid.data.di

import com.pedroid.data.dao.TasksDao
import com.pedroid.data.local.TasksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesTasksDao(
        database: TasksDatabase
    ): TasksDao = database.tasksDao()
}