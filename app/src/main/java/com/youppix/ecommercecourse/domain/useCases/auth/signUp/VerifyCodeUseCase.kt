package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow

class VerifyCodeUseCase(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(email : String , verifyCode: String) : Flow<Resource<AuthResponse>> {
        return signUpRepository.verifyCode(email, verifyCode)
    }
}