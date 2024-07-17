package com.youppix.ecommercecourse.domain.useCases.auth.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository

class CheckPhoneUseCase(
    private val signUpRepository: SignUpRepository
) {
    operator fun invoke(phone: String, context: Context): Resource<Boolean> {
        return signUpRepository.checkPhone(phone, context)
    }

}