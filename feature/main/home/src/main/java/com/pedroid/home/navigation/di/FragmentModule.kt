package com.pedroid.home.navigation.di

import com.pedroid.communicator.HomeFeatureCommunicator
import com.pedroid.home.navigation.HomeFeatureCommunicatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface FragmentModule {

    @Binds
    fun bindCommunicator(homeFeatureCommunicatorImpl: HomeFeatureCommunicatorImpl): HomeFeatureCommunicator
}