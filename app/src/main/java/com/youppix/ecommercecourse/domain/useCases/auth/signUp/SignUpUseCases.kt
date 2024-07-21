package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import com.youppix.ecommercecourse.domain.useCases.auth.CheckEmailUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckPasswordUseCase

data class SignUpUseCases (
    val checkEmail : CheckEmailUseCase,
    val checkPassword: CheckPasswordUseCase ,
    val checkUserName: CheckUserNameUseCase  ,
    val checkPhone : CheckPhoneUseCase,
    val addUser : AddUserUseCase
)