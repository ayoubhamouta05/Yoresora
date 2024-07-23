package com.youppix.ecommercecourse.data.repository.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.AuthService
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.model.User
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import kotlinx.coroutines.flow.Flow

class SignUpRepositoryImpl(
    private val authService : AuthService
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


    override suspend fun addUser(name : String , email : String , phone : String , password : String): Flow<Resource<AuthResponse>> {
        return authService.addUser(name  , email  , phone , password )
    }

    override suspend fun verifyCode(email:String , verifyCode: String): Flow<Resource<AuthResponse>> {
        return authService.verifyCode(email, verifyCode)
    }
}