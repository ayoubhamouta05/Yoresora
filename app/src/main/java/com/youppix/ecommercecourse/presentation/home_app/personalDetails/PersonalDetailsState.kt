package com.youppix.ecommercecourse.presentation.home_app.personalDetails

import com.youppix.ecommercecourse.domain.model.user.User

data class PersonalDetailsState(
    val user : User = User(),
    val newPassword : String = "" ,
    val oldPassword : String = "",
    val showPassword: Boolean = false,
    val showNewPassword: Boolean = false,
    val userNameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val newPasswordError: String? = null,
)
