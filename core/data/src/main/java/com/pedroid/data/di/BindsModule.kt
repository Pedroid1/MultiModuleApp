package com.pedroid.data.di

import com.pedroid.data.repositories.preferences.PrefsRepository
import com.pedroid.data.repositories.preferences.AppPreferencesRepositoryImpl
import com.pedroid.data.repositories.tasks.TasksRepository
import com.pedroid.data.repositories.tasks.TasksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    internal abstract fun bindsPrefsRepository(
        appPreferencesRepositoryImpl: AppPreferencesRepositoryImpl
    ): PrefsRepository

    @Binds
    internal abstract fun bindsTasksRepository(
        tasksRepositoryImpl: TasksRepositoryImpl
    ): TasksRepository
}