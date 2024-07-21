package com.youppix.ecommercecourse.presentation.auth.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.useCases.auth.login.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    private var _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState


    fun updateEmail(value: String) {
        _loginState.value = loginState.value.copy(
            email = value,
        )
    }

    fun updatePassword(value: String) {
        _loginState.value = loginState.value.copy(
            password = value,
        )
    }

    fun updateRememberMe(value: Boolean) {
        _loginState.value = loginState.value.copy(
            rememberMe = value,
        )
    }

    fun showPassword(showPassword: Boolean) {
        _loginState.value = loginState.value.copy(
            showPassword = showPassword,
        )
    }

    private fun checkEmail(email: String, context: Context): Boolean {

        when (val result = loginUseCases.checkEmail(email, context)) {
            is Resource.Loading -> {
                // Handle loading state
            }

            is Resource.Error -> {
                _loginState.value = loginState.value.copy(
                    emailError = result.message,
                )
            }

            is Resource.Successful -> {
                _loginState.value = loginState.value.copy(
                    emailError = null
                )

            }
        }
        return loginState.value.emailError.isNullOrEmpty()
    }

    private fun checkPassword(password: String, context: Context): Boolean {


        when (val result = loginUseCases.checkPassword(password, context)) {
            is Resource.Loading -> {
                // Handle loading state
            }

            is Resource.Error -> {
                _loginState.value = loginState.value.copy(
                    passwordError = result.message,
                )
            }

            is Resource.Successful -> {
                _loginState.value = loginState.value.copy(
                    passwordError = null
                )
            }
        }

        return loginState.value.passwordError.isNullOrEmpty()
    }

    fun validateForm(email: String, password: String, context: Context): Boolean {

        return checkEmail(email, context) && checkPassword(password, context)


    }

    suspend fun login(email : String , password : String) {
        loginUseCases.login(email , password).onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _loginState.value = loginState.value.copy(
                        isLoading = true,
                    )
                }
                is Resource.Error -> {
                    _loginState.value = loginState.value.copy(
                        isLoading = false,
                        loginError = result.message,
                        loginSuccessful = false,
                    )
                    Log.d("LoginViewModel" , "result : ${result.message}")
                }
                is Resource.Successful->{
                    _loginState.value = loginState.value.copy(
                        isLoading = false,
                        loginSuccessful = true,
                        loginError = null
                    )
                    Log.d("LoginViewModel" , "result : ${result.data}")
                }
            }

        }.launchIn(viewModelScope)
    }

    fun resetState(){
        _loginState.value = loginState.value.copy(
            loginSuccessful = false ,
            loginError = null
        )
    }


}