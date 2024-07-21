package com.youppix.ecommercecourse.data.repository.login

import android.content.Context
import android.util.Log
import android.util.Patterns
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.AuthService
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val authService: AuthService
) : LoginRepository {

    override fun checkEmail(email: String, context: Context): Resource<Boolean> {
        return Constant.checkEmail(email, context)
    }

    override fun checkPassword(password: String, context: Context): Resource<Boolean> {
        return Constant.checkPassword(password, context)
    }

    override suspend fun login(email: String, password: String): Flow<Resource<AuthResponse>> {
        return authService.login(email, password)
    }
}