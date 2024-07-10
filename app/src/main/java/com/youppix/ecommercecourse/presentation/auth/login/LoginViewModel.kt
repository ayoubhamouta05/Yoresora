package com.youppix.ecommercecourse.presentation.auth.login

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _password = mutableStateOf("")
    val password: State<String> = _password

    private var _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private var _rememberMe = mutableStateOf(false)
    val rememberMe: State<Boolean> = _rememberMe

    private var _emailError = mutableStateOf("")
    var emailError: State<String> = _emailError

    private var _passwordError = mutableStateOf("")
    var passwordError: State<String> = _passwordError

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updateRememberMe(value: Boolean) {
        _rememberMe.value = value
    }

    fun showOrHidePassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    private fun validateEmail(emptyErrorMsg: String, wrongPatternErrorMsg: String): Boolean {
        val trimEmail = email.value.trim()
        var isValid = true
        var errorMessage = ""

        if (trimEmail.isBlank() || trimEmail.isEmpty()) {
            errorMessage = emptyErrorMsg
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trimEmail).matches()) {
            errorMessage = wrongPatternErrorMsg
            isValid = false
        }

        _emailError.value = errorMessage
        return isValid
    }

    private fun validatePassword(emptyErrorMsg: String, wrongPatternErrorMsg: String): Boolean {
        val trimPassword = password.value.trim()
        var isValid = true
        var errorMessage = ""

        if (trimPassword.isBlank() || trimPassword.isEmpty()) {
            errorMessage = emptyErrorMsg
            isValid = false
        } else if (trimPassword.length < 6) {
            errorMessage = wrongPatternErrorMsg
            isValid = false
        }

        _passwordError.value = errorMessage
        return isValid
    }


    fun validateForm(
        emailEmptyErrorMsg: String,
        emailWrongPatternErrorMsg: String,
        passwordEmptyErrorMsg: String,
        passwordWrongPatternErrorMsg: String
    ) {
        if (validateEmail(emailEmptyErrorMsg, emailWrongPatternErrorMsg) && validatePassword(
                passwordEmptyErrorMsg,
                passwordWrongPatternErrorMsg
            )
        ) {
            println("login successful")
        }
    }


}