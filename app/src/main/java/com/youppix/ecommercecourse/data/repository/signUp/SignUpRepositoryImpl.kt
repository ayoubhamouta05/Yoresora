package com.youppix.ecommercecourse.data.repository.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.SignUpService
import com.youppix.ecommercecourse.data.remote.auth.dto.SignUpResponse
import com.youppix.ecommercecourse.domain.model.User
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class SignUpRepositoryImpl(
    private val signUpService : SignUpService
) : SignUpRepository {
    override fun checkUserName(userName: String, context: Context): Resource<Boolean> {
        return Constant.checkUserName(userName , context)
    }

    override fun checkEmail(email: String, context: Context): Resource<Boolean> {
        return Constant.checkEmail(email , context)
    }

    override fun checkPhone(phone: String, context: Context): Resource<Boolean> {
       return Constant.checkPhone(phone , context)
    }

    override fun checkPassword(password: String, context: Context): Resource<Boolean> {
        return Constant.checkPassword(password , context)
    }

    override suspend fun addUser(user: User): Flow<Resource<SignUpResponse>> {
        return signUpService.addUser(user)
    }
}