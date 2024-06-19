package com.pedroid.data.repository.preferences

import android.content.Context
import com.pedroid.common.util.Constants
import com.pedroid.domain.repository.PrefsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferencesRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : PrefsRepository {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun isUserOnboarded(): Boolean {
        return sharedPreferences.getBoolean(Constants.ONBOARDING_KEY_PREFS, false)
    }

    override fun setUserOnboarded(onboarded: Boolean) {
        sharedPreferences.edit().putBoolean(Constants.ONBOARDING_KEY_PREFS, onboarded).apply()
    }
}