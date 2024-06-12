package com.pedroid.data.di

import android.content.Context
import androidx.room.Room
import com.pedroid.data.local.TasksDatabase
import com.pedroid.data.repositories.preferences.AppPreferencesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesPrefsRepository(
        @ApplicationContext context: Context
    ) = AppPreferencesRepositoryImpl(context)

    @Provides
    @Singleton
    fun providesTasksDatabase(
        @ApplicationContext context: Context
    ): TasksDatabase = Room.databaseBuilder(
        context,
        TasksDatabase::class.java,
        "tasks-database"
    ).build()
}