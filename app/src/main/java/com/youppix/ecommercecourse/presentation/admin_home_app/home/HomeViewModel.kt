package com.youppix.ecommercecourse.presentation.admin_home_app.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ScreenModel {

    private var _state = mutableStateOf(HomeState(isLoading = true))
    val state : State<HomeState> = _state



}