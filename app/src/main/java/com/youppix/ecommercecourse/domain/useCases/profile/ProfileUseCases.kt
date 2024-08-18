package com.youppix.ecommercecourse.domain.useCases.profile

import com.youppix.ecommercecourse.domain.useCases.appLanguage.SaveAppLanguageUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckEmailUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckPasswordUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.signUp.CheckPhoneUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.signUp.CheckUserNameUseCase

data class ProfileUseCases (
    val saveUserData : SaveUserData,
    val saveAppLanguage : SaveAppLanguageUseCase,
    val uploadImage : UploadImageUseCase,
    val getUserData : GetUserDataUseCase,
    val updatePersonalDetails : UpdatePersonalDetailsUseCase,
    val checkEmail : CheckEmailUseCase,
    val checkPassword: CheckPasswordUseCase,
    val checkUserName: CheckUserNameUseCase,
    val checkPhone : CheckPhoneUseCase,
    val checkEmailAvailability : CheckEmailAvailabilityUseCase
)