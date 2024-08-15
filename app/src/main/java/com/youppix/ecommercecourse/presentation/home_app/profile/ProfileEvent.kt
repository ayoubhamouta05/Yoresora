package com.youppix.ecommercecourse.presentation.home_app.profile

sealed class ProfileEvent {

    data object Logout : ProfileEvent()
    data object ShowDialog : ProfileEvent()
    data object HideDialog : ProfileEvent()
    data object ToggleNotification : ProfileEvent()

}