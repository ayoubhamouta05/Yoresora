package com.youppix.ecommercecourse.domain.useCases.auth.login

import com.youppix.ecommercecourse.domain.useCases.auth.CheckEmailUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckPasswordUseCase

data class LoginUseCases(
    val checkEmail: CheckEmailUseCase,
    val checkPassword: CheckPasswordUseCase,
    val login : LoginUseCase
)