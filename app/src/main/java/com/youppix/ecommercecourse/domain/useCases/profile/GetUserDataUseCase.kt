package com.youppix.ecommercecourse.domain.useCases.profile

import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository

class GetUserDataUseCase(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(key  :String , defaultValue : String) : String =
        profileRepository.getUserInfo(key , defaultValue)
}