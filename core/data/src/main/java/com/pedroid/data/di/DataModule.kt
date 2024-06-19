package com.pedroid.data.di

import android.content.Context
import com.pedroid.data.repository.preferences.AppPreferencesRepositoryImpl
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
}