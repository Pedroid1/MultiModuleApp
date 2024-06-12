package com.pedroid.data.repository

interface PrefsRepository {
    fun isUserOnboarded(): Boolean
    fun setUserOnboarded(onboarded: Boolean)
}