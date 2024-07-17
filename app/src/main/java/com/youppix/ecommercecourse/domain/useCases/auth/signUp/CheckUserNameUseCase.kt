package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository

class CheckUserNameUseCase (
    private val signUpRepository: SignUpRepository
){
    operator fun invoke(userName : String , context : Context) : Resource<Boolean>{
        return signUpRepository.checkUserName(userName,context)
    }

}