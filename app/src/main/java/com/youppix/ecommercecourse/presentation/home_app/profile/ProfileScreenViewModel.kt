package com.youppix.ecommercecourse.presentation.home_app.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import com.youppix.ecommercecourse.common.Constant.APP_ENTRY
import com.youppix.ecommercecourse.domain.useCases.profile.ProfileUseCases
import javax.inject.Inject

class ProfileScreenViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) : ScreenModel {

    private var _state  = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    fun onEvent(event : ProfileEvent){
        when(event){
            is ProfileEvent.Logout -> {
                logout()
                onEvent(ProfileEvent.HideDialog)
            }
            is ProfileEvent.ShowDialog -> {
                _state.value = state.value.copy(
                    showDialog = true
                )
            }
            is ProfileEvent.HideDialog ->{
                _state.value = state.value.copy(
                    showDialog = false
                )
            }
            is ProfileEvent.ToggleNotification -> {
                _state.value = state.value.copy(
                    isNotificationEnable = !state.value.isNotificationEnable
                )
            }

        }
    }

    private fun logout(){
        profileUseCases.logout(APP_ENTRY , "1")
    }


}