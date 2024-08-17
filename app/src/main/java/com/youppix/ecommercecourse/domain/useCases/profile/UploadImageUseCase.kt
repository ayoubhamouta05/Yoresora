package com.youppix.ecommercecourse.domain.useCases.profile

import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository
import java.io.File

class UploadImageUseCase(
    private val profileRepository: ProfileRepository
) {

    suspend operator fun invoke(userId :Int ,file: File) =
        profileRepository.uploadImage(userId ,file)


}