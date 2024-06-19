package com.pedroid.data.di

import com.pedroid.data.repository.preferences.AppPreferencesRepositoryImpl
import com.pedroid.data.repository.tasks.TasksRepositoryImpl
import com.pedroid.domain.repository.PrefsRepository
import com.pedroid.domain.repository.TasksRepository
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