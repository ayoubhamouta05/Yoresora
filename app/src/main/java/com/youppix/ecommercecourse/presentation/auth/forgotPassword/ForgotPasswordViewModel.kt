package com.youppix.ecommercecourse.presentation.auth.forgotPassword

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword.ForgotPasswordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel@Inject constructor(
    private val forgotPasswordUseCases: ForgotPasswordUseCases
) : ViewModel() {

    private var _forgotPasswordState = mutableStateOf(ForgotPasswordState())
    val forgotPasswordState : State<ForgotPasswordState> = _forgotPasswordState

    fun setState(newState: ForgotPasswordState) {
        _forgotPasswordState.value = newState
    }

    fun updateEmail(value : String){
        _forgotPasswordState.value = forgotPasswordState.value.copy(
            email = value
        )
    }

    fun updatePassword (value : String){
        _forgotPasswordState.value = forgotPasswordState.value.copy(
            newPassword = value
        )
    }

    fun updateVerificationCode(value : String){
        _forgotPasswordState.value = forgotPasswordState.value.copy(
            verificationCode = value
        )
    }

    fun showPassword(showPassword : Boolean){
        _forgotPasswordState.value = forgotPasswordState.value.copy(
            showPassword = showPassword
        )
    }

     fun checkEmail(email: String,context: Context): Boolean {

        when (val result = forgotPasswordUseCases.checkEmail(email,context)) {
            is Resource.Loading -> {
                // Handle loading state
            }

            is Resource.Error -> {
                _forgotPasswordState.value = forgotPasswordState.value.copy(
                    emailError = result.message,
                )
            }

            is Resource.Successful -> {
                _forgotPasswordState.value = _forgotPasswordState.value.copy(
                    emailError = null
                )

            }
        }
        return forgotPasswordState.value.emailError.isNullOrEmpty()
    }

     fun checkPassword(password: String,context: Context): Boolean {


        when (val result = forgotPasswordUseCases.checkPassword(password,context)) {
            is Resource.Loading -> {
                // Handle loading state
            }

            is Resource.Error -> {
                _forgotPasswordState.value = forgotPasswordState.value.copy(
                    passwordError = result.message ,
                )
            }

            is Resource.Successful -> {
                _forgotPasswordState.value = forgotPasswordState.value.copy(
                    passwordError = null
                )
            }
        }

        return forgotPasswordState.value.passwordError.isNullOrEmpty()
    }
}