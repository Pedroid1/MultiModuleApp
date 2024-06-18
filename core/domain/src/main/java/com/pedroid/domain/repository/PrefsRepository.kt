package com.pedroid.domain.repository

interface PrefsRepository {
    fun isUserOnboarded(): Boolean
    fun setUserOnboarded(onboarded: Boolean)
}