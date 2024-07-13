package com.youppix.ecommercecourse.presentation.auth.forgotPassword

import java.io.Serializable

data class ForgotPasswordState(
    val isLoading : Boolean = false,
    val email : String = "" ,
    val verificationCode : String = "" ,
    val newPassword : String= "" ,
    val showPassword : Boolean = false ,
    val emailError : String? =null ,
    val verificationCodeError : Boolean? = null,
    val verificationResultMessage : String ="",
    val passwordError : String? = "",
    val resetPasswordSuccessful : Boolean = false ,
) : Serializable
