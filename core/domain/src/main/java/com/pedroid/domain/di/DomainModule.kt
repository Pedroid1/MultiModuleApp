package com.pedroid.domain.di

import com.pedroid.domain.tasks.GetTasksUseCase
import com.pedroid.domain.tasks.GetTasksUseCaseImpl
import com.pedroid.domain.ui.task.TaskValidationUseCase
import com.pedroid.domain.ui.task.TaskValidationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun bindGetTasksUseCase(tasksUseCaseImpl: GetTasksUseCaseImpl): GetTasksUseCase

    @Binds
    abstract fun bindValidationTaskUseCase(validationUseCase: TaskValidationUseCaseImpl): TaskValidationUseCase
}