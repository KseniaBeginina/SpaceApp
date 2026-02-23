package com.beginina.spaceapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beginina.domain.usecases.GetOnboardingStateUseCase
import com.beginina.domain.usecases.SetOnboardingStateUseCase
import com.beginina.spaceapp.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOnboardingStateUseCase: GetOnboardingStateUseCase,
    private val setOnboardingStateUseCase: SetOnboardingStateUseCase
): ViewModel() {
    private val _startDestination = mutableStateOf<String>(Routes.ONBOARDING)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            val isShown = getOnboardingStateUseCase()
            _startDestination.value = if (isShown) {
                Routes.MAIN
            } else {
                Routes.ONBOARDING
            }
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            setOnboardingStateUseCase()
        }
    }
}