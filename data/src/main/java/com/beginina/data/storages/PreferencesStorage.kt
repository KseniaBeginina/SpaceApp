package com.beginina.data.storages

import android.content.SharedPreferences

class PreferencesStorage(
    private val sharedPreferences: SharedPreferences
) {
    fun isOnboardingShown() = sharedPreferences.getBoolean("onboarding_state", false)

    fun setOnboardingShown() = sharedPreferences.edit().putBoolean("onboarding_state", true).apply()
}