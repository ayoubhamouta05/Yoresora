package com.youppix.ecommercecourse.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private var _email = mutableStateOf("")
    val email : State<String> = _email

    private var _password = mutableStateOf("")
    val password : State<String> = _password

    private var _showPassword = mutableStateOf(false)
    val showPassword : State<Boolean> = _showPassword

    private var _rememberMe = mutableStateOf(false)
    val rememberMe : State<Boolean> = _rememberMe

    fun updateEmail(value : String){
        _email.value = value
    }

    fun updatePassword (value : String){
        _password.value = value
    }

    fun updateRememberMe(value : Boolean){
        _rememberMe.value = value
    }

    fun showOrHidePassword(showPassword : Boolean){
        _showPassword.value = showPassword
    }


}