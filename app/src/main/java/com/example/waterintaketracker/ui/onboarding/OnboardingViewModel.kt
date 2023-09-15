package com.example.waterintaketracker.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {
    private val _stateOnboarding = MutableStateFlow<Int>(0)
    val stateOnboarding: StateFlow<Int> = _stateOnboarding
    fun loadState(onboarding: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateOnboarding.emit(onboarding)
        }
    }

}