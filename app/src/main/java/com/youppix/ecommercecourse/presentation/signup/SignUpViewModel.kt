package com.youppix.ecommercecourse.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    private var _email = mutableStateOf("")
    val email : State<String> = _email

    private var _password = mutableStateOf("")
    val password : State<String> = _password


    private var _showPassword = mutableStateOf(false)
    val showPassword : State<Boolean> = _showPassword

    private var _userName = mutableStateOf("")
    val userName : State<String> = _userName

    private var _phone = mutableStateOf("")
    val phone : State<String> = _phone

    fun updateEmail(value : String){
        _email.value = value
    }

    fun updatePassword (value : String){
        _password.value = value
    }

    fun updatePhone(value : String){
        _phone.value = value
    }

    fun updateUserName(value : String){
        _userName.value = value
    }

    fun showOrHidePassword(showPassword : Boolean){
        _showPassword.value = showPassword
    }


}