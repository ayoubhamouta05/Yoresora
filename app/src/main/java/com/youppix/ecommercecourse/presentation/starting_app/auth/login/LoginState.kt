package com.youppix.ecommercecourse.presentation.starting_app.auth.login

import java.io.Serializable


data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val rememberMe: Boolean = false,
    val loginSuccessful: Boolean = false,
    val loginError: String? = null,
    val needUserApprove :Boolean?=null
) : Serializable
