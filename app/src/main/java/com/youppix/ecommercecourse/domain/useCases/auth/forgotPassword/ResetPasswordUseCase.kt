package com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword

import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository

class ResetPasswordUseCase(
    private val forgotPasswordRepository: ForgotPasswordRepository
) {
}