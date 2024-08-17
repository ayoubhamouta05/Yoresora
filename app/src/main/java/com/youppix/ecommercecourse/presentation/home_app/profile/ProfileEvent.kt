package com.youppix.ecommercecourse.presentation.home_app.profile

import java.io.File

sealed class ProfileEvent {

    data class SetUserId(val userId: Int) : ProfileEvent()
    data object Logout : ProfileEvent()
    data object ShowDialog : ProfileEvent()
    data object HideDialog : ProfileEvent()
    data object ToggleNotification : ProfileEvent()
    data class UploadImage(val userId: Int, val file: File) : ProfileEvent()
    data object GetUserData : ProfileEvent()
    data class SaveAppLanguage(val lang : String ) : ProfileEvent()

}