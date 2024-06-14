package com.pedroid.domain.preferences

import com.pedroid.data.repositories.preferences.PrefsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AppPreferencesUseCaseTest {

    private val mockPrefsRepository: PrefsRepository = mockk(relaxed = true)
    private val appPreferencesUseCase: AppPreferencesUseCase = AppPreferencesUseCaseImpl(mockPrefsRepository)

    @Test
    fun `given isUserOnboarded returns true when isUserOnboarded is called then repository isUserOnboarded should be called`() {
        every { mockPrefsRepository.isUserOnboarded() } returns true

        appPreferencesUseCase.isUserOnboarded()

        verify { mockPrefsRepository.isUserOnboarded() }
    }

    @Test
    fun `given setUserOnboarded is called with false when setUserOnboarded is called then repository setUserOnboarded should be called with false`() {
        every { mockPrefsRepository.setUserOnboarded(any()) } returns Unit

        appPreferencesUseCase.setUserOnboarded(false)

        verify { mockPrefsRepository.setUserOnboarded(false) }
    }
}