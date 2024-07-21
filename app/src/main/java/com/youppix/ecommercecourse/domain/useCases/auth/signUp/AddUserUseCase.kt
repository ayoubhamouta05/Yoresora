package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.SignUpResponse
import com.youppix.ecommercecourse.domain.model.User
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow
class AddUserUseCase(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(user : User) : Flow<Resource<SignUpResponse>> {
        return signUpRepository.addUser(user)
    }

}