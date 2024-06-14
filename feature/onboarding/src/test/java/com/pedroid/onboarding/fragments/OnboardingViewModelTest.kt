package com.pedroid.onboarding.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.pedroid.domain.preferences.AppPreferencesUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OnboardingViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: AppPreferencesUseCase = mockk(relaxed = true)
    lateinit var vm: OnboardingViewModel

    @Before
    fun setup() {
        vm = OnboardingViewModel(useCase)
    }

    @Test
    fun `given the initial state of ViewModel, when ViewModel is instantiated, then isUserOnboarded should be called`() {
        verify { useCase.isUserOnboarded() }
    }

    @Test
    fun `given the initial state of ViewModel, when ViewModel is instantiated, then isUserOnboarded should be updated`() {
        assertThat(vm.isUserOnboarded.value).isNotNull()
    }

    @Test
    fun `WHEN setUserOnboarded is called THEN call useCase setUserOnboarded`() {
        vm.setUserOnboarded(true)
        verify { useCase.setUserOnboarded(any()) }
    }
}