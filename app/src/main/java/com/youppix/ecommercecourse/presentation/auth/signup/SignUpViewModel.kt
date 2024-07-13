package com.youppix.ecommercecourse.presentation.auth.signup

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.youppix.ecommercecourse.presentation.auth.forgotPassword.ForgotPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private var _signUpState = mutableStateOf(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

    fun setState(newState: SignUpState) {
        _signUpState.value = newState
    }

    private var _email = mutableStateOf("")
    val email: State<String> = _email

    private var _password = mutableStateOf("")
    val password: State<String> = _password


    private var _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private var _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private var _phone = mutableStateOf("")
    val phone: State<String> = _phone

    fun updateEmail(value: String) {
        _email.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun updatePhone(value: String) {
        _phone.value = value
    }

    fun updateUserName(value: String) {
        _userName.value = value
    }

    fun showOrHidePassword(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun updateVerificationCode(value : String){
        _signUpState.value = signUpState.value.copy(
            verificationCode = value
        )
    }

    private var _emailError = mutableStateOf("")
    var emailError: State<String> = _emailError

    private var _passwordError = mutableStateOf("")
    var passwordError: State<String> = _passwordError

    fun validateUsername(name: String?): Boolean {
        val regex = "^[A-Za-z]\\w{5,29}$"
        val p: Pattern = Pattern.compile(regex)
        if (name == null) {
            return false
        }
        val m: Matcher = p.matcher(name)
        return m.matches()
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
        passwordWrongPatternErrorMsg: String,
    ) {
        if (validateEmail(emailEmptyErrorMsg, emailWrongPatternErrorMsg) &&
            validatePassword(passwordEmptyErrorMsg, passwordWrongPatternErrorMsg )
        ) {
            println("login successful")
        }
    }


}