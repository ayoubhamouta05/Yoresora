package com.youppix.ecommercecourse.presentation.auth.signup

import java.io.Serializable

data class SignUpState(
    val email : String = "",
    val verificationCode : String = ""
) : Serializable
