package com.youppix.ecommercecourse.domain.useCases.profile

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.Flow

class UpdatePersonalDetailsUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(
        userId: Int, name: String, email: String, phone: String,
        oldPassword: String, newPassword: String ,
        userCustomerId : String
    ): Flow<Resource<AuthResponse>> =
        profileRepository.updatePersonalDetails(
            userId = userId,
            name =name,
            email = email,
            phone =phone,
            oldPassword = oldPassword,
            newPassword =newPassword,
            userCustomerId = userCustomerId
        )

}