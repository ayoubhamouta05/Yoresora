package com.youppix.ecommercecourse.domain.useCases.profile

data class ProfileUseCases (
    val saveUserData : SaveUserData,
    val uploadImage : UploadImageUseCase,
    val getUserData : GetUserDataUseCase
)