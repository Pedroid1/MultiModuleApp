package com.pedroid.data.repository.impl

import android.content.Context
import com.pedroid.common.util.Constants
import com.pedroid.data.repository.PrefsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferencesRepository @Inject constructor(
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