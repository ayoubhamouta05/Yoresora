package com.youppix.ecommercecourse.data.repository.forgotPassword

import android.content.Context
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.AuthService
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository
import kotlinx.coroutines.flow.Flow

class ForgotPasswordRepositoryImpl(
    private val authService: AuthService
): ForgotPasswordRepository {
    override fun checkEmail(email: String , context: Context): Resource<Boolean> {
        return Constant.checkEmail(email ,context)
    }

    override fun checkPassword(password: String , context: Context): Resource<Boolean> {
        return Constant.checkPassword(password, context)
    }

    override suspend fun checkEmail(email: String): Flow<Resource<AuthResponse>> {
        return authService.checkEmail(email)
    }

    override suspend fun verifyCode(
        email: String,
        verifyCode: String
    ): Flow<Resource<AuthResponse>> {
        return authService.verifyCode(email,verifyCode, verifyCodeForgotPassword = true)
    }

    override suspend fun resetPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResponse>> {
        return authService.resetPassword(email, password)
    }
}