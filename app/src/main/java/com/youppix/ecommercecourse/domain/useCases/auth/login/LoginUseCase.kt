package com.youppix.ecommercecourse.domain.useCases.auth.login

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email : String , password : String) : Flow<Resource<AuthResponse>>{
        return loginRepository.login(email , password)
    }
}