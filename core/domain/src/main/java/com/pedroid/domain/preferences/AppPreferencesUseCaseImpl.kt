package com.pedroid.domain.preferences

import com.pedroid.data.repositories.preferences.PrefsRepository
import javax.inject.Inject

class AppPreferencesUseCaseImpl @Inject constructor(
    private val prefsRepository: PrefsRepository
): AppPreferencesUseCase {
    override fun isUserOnboarded(): Boolean {
        return prefsRepository.isUserOnboarded()
    }

    override fun setUserOnboarded(onboarded: Boolean) {
        return prefsRepository.setUserOnboarded(onboarded)
    }
}