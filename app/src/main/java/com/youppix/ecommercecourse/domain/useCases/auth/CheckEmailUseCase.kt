package com.youppix.ecommercecourse.domain.useCases.auth

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class CheckEmailUseCase(
    private val loginRepository: LoginRepository? = null,
    private val forgotPasswordRepository: ForgotPasswordRepository? = null
) {

    operator fun invoke(email: String,context: Context): Resource<Boolean> {
        return loginRepository?.checkEmail(email ,context)
            ?: forgotPasswordRepository?.checkEmail(email ,context)
            ?: Resource.Error("")

    }

}