package com.pedroid.data.di

import android.content.Context
import com.pedroid.data.repository.impl.AppPreferencesRepository
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
    fun providePrefsRepository(
        @ApplicationContext context: Context
    ) = AppPreferencesRepository(context)
}