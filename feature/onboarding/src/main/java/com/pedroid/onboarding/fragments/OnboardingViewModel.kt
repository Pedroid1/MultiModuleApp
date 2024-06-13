package com.pedroid.onboarding.fragments

import androidx.lifecycle.MutableLiveData
import com.pedroid.common.base.BaseViewModel
import com.pedroid.domain.preferences.AppPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appPreferencesUseCase: AppPreferencesUseCase
) : BaseViewModel() {

    private val _isUserOnboarded = MutableLiveData<Boolean>()
    val isUserOnboarded get() = _isUserOnboarded

    init {
        _isUserOnboarded.value = appPreferencesUseCase.isUserOnboarded()
    }

    fun setUserOnboarded(value: Boolean) = appPreferencesUseCase.setUserOnboarded(value)
}