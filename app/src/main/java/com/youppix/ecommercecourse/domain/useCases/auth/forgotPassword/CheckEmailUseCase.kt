package com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class CheckEmailUseCase(
    private val forgotPasswordRepository: ForgotPasswordRepository
) {
    suspend operator fun invoke(email:String) : Flow<Resource<AuthResponse>>{
        return forgotPasswordRepository.checkEmail(email)
    }
}