package com.youppix.ecommercecourse.domain.useCases.profile

import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository

class CheckEmailAvailabilityUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(userId: Int , email : String) =
        profileRepository.checkEmailAvailability(userId , email)

}