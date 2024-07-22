package com.youppix.ecommercecourse.domain.repository.forgotPassword

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import kotlinx.coroutines.flow.Flow

interface ForgotPasswordRepository {

    fun checkEmail(email : String , context: Context) : Resource<Boolean>

    fun checkPassword(password : String,context: Context ) : Resource<Boolean>

    suspend fun checkEmail(email:String) : Flow<Resource<AuthResponse>>

    suspend fun verifyCode(email  :String , verifyCode: String) : Flow<Resource<AuthResponse>>

    suspend fun resetPassword(email : String, password : String) : Flow<Resource<AuthResponse>>
}