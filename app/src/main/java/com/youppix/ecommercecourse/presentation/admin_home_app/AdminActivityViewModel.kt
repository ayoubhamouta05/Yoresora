package com.youppix.ecommercecourse.presentation.admin_home_app

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminActivityViewModel @Inject constructor() : ViewModel() {
    private var _state = mutableStateOf(AdminActivityState())
    val state: State<AdminActivityState> = _state

    fun setCurrentScreen(value: Int) {
        _state.value = state.value.copy(currentScreen = value)
    }
}