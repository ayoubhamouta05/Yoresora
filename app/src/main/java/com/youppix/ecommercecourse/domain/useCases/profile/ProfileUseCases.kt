package com.youppix.ecommercecourse.domain.useCases.profile

import com.youppix.ecommercecourse.domain.useCases.appLanguage.SaveAppLanguageUseCase

data class ProfileUseCases (
    val saveUserData : SaveUserData,
    val saveAppLanguage : SaveAppLanguageUseCase,
    val uploadImage : UploadImageUseCase,
    val getUserData : GetUserDataUseCase
)