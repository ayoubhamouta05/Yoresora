package com.youppix.ecommercecourse.presentation.auth.forgotPassword

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.youppix.ecommercecourse.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel@Inject constructor(

) : ViewModel() {

    private var _email = mutableStateOf("")
    val email : State<String> = _email

    private var _emailError = mutableStateOf("")
    val emailError : State<String> = _emailError

    fun updateEmail(value : String){
        _email.value = value
    }

    private var _password = mutableStateOf("")
    val password : State<String> = _password

    private var _showPassword = mutableStateOf(false)
    val showPassword : State<Boolean> = _showPassword

    private var _passwordError = mutableStateOf("")
    var passwordError : State<String> = _passwordError

    fun updatePassword (value : String){
        _password.value = value
    }

    fun showOrHidePassword(showPassword : Boolean){
        _showPassword.value = showPassword
    }

    fun validateEmail(emptyErrorMsg : String , wrongPatternErrorMsg : String): Boolean {
        val trimEmail = email.value.trim()
        var isValid = true
        var errorMessage = ""

        if (trimEmail.isBlank() || trimEmail.isEmpty()) {
            errorMessage =  emptyErrorMsg//"Please fill email field"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trimEmail).matches()) {
            errorMessage = wrongPatternErrorMsg// "Wrong email Format"
            isValid = false
        }

        _emailError.value = errorMessage
        return isValid
    }

    fun validatePassword(emptyErrorMsg : String , wrongPatternErrorMsg : String): Boolean {
        val trimPassword = password.value.trim()
        var isValid = true
        var errorMessage = ""

        if (trimPassword.isBlank() || trimPassword.isEmpty()) {
            errorMessage = emptyErrorMsg//"Please fill password field"
            isValid = false
        } else if (trimPassword.length < 6) {
            errorMessage = wrongPatternErrorMsg// "Password must more than 6 character"
            isValid = false
        }

        _passwordError.value = errorMessage
        return isValid
    }
}