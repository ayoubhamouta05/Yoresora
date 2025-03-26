package com.youppix.ecommercecourse.presentation.user_home_app.profile

import android.content.Context
import java.io.File

sealed class ProfileEvent {

    data class SetUserId(val userId: Int) : ProfileEvent()
    data object Logout : ProfileEvent()
    data object ShowDialog : ProfileEvent()
    data object HideDialog : ProfileEvent()
    data class ToggleNotification(val value : Boolean) : ProfileEvent()
    data class UploadImage(val userId: Int, val file: File) : ProfileEvent()
    data class GetUserData(val context : Context) : ProfileEvent()
    data class SaveAppLanguage(val lang : String ) : ProfileEvent()

}