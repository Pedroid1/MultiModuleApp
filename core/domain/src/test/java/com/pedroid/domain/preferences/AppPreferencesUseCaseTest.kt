package com.pedroid.domain.preferences

import com.pedroid.data.repositories.preferences.PrefsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AppPreferencesUseCaseTest {

    private val prefsRepository: PrefsRepository = mockk(relaxed = true)
    private val appPreferencesUseCaseImpl = AppPreferencesUseCaseImpl(prefsRepository)

    @Test
    fun `isUserOnboarded calls repository isUserOnboarded`() {
        every { prefsRepository.isUserOnboarded() } returns true

        appPreferencesUseCaseImpl.isUserOnboarded()

        verify { prefsRepository.isUserOnboarded() }
    }

    @Test
    fun `setUserOnboarded calls repository setUserOnboarded`() {
        every { prefsRepository.setUserOnboarded(any()) } returns Unit

        appPreferencesUseCaseImpl.setUserOnboarded(false)

        verify { prefsRepository.setUserOnboarded(false) }
    }
}