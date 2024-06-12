package com.pedroid.data.di

import com.pedroid.data.repository.PrefsRepository
import com.pedroid.data.repository.impl.AppPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    internal abstract fun bindsPrefsRepository(
        appPreferencesRepository: AppPreferencesRepository
    ): PrefsRepository
}