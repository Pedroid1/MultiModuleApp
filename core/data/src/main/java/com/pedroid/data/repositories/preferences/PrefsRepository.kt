package com.pedroid.data.repositories.preferences

interface PrefsRepository {
    fun isUserOnboarded(): Boolean
    fun setUserOnboarded(onboarded: Boolean)
}