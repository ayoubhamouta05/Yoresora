package com.youppix.ecommercecourse.domain.useCases.auth

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow

class CheckPasswordUseCase(
    private val loginRepository: LoginRepository? = null,
    private val forgotPasswordRepository: ForgotPasswordRepository? = null,
    private val signUpRepository: SignUpRepository? = null
) {

    operator fun invoke(password: String ,context: Context): Resource<Boolean> {
        return loginRepository?.checkPassword(password , context)
            ?: forgotPasswordRepository?.checkPassword(password, context)
            ?: signUpRepository?.checkPassword(password, context)
            ?: Resource.Error("")
    }

}