package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository

class CheckUserNameUseCase (
    private val signUpRepository: SignUpRepository? = null,
    private val profileRepository: ProfileRepository? = null
){
    operator fun invoke(userName : String , context : Context) : Resource<Boolean>{
        return signUpRepository?.checkUserName(userName,context)
            ?:profileRepository?.checkUserName(userName,context)
            ?:Resource.Error("")
    }

}