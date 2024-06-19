package com.pedroid.onboarding.navigation.di

import com.pedroid.navigation.NavigationNode
import com.pedroid.onboarding.navigation.OnboardingNavigationNode
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @IntoSet
    @Binds
    fun bindNavigationNode(onboardingNavigationNode: OnboardingNavigationNode): NavigationNode
}