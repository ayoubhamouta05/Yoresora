package com.youppix.ecommercecourse.presentation.home_app.personalDetails

sealed class PersonalDetailsEvent {

    data class UpdateUserName(val name  : String) : PersonalDetailsEvent()
    data class UpdateEmail(val email  : String) : PersonalDetailsEvent()
    data class UpdatePhone(val phone  : String) : PersonalDetailsEvent()
    data class UpdatePassword(val password  : String) : PersonalDetailsEvent()
    data class UpdateNewPassword(val newPassword  : String) : PersonalDetailsEvent()
    data object ToggleShowPassword : PersonalDetailsEvent()
    data object ToggleShowNewPassword : PersonalDetailsEvent()

}