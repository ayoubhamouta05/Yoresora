package com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword

import com.youppix.ecommercecourse.domain.useCases.auth.CheckEmailUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckPasswordUseCase

data class ForgotPasswordUseCases(
    val checkEmail: CheckEmailUseCase,
    val checkPassword: CheckPasswordUseCase,
    val resetPassword : ResetPasswordUseCase,
    val checkEmailDb: com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword.CheckEmailUseCase,
    val verifyCode : VerifyCodeUseCase
)
