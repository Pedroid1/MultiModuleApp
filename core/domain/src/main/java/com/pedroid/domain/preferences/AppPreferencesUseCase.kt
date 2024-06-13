package com.pedroid.domain.preferences

interface AppPreferencesUseCase {
    fun isUserOnboarded(): Boolean
    fun setUserOnboarded(onboarded: Boolean)
}