package com.youppix.ecommercecourse.domain.repository.login

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.auth.dto.LoginResponse
import com.youppix.ecommercecourse.presentation.auth.login.LoginState
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun checkEmail (email : String , context: Context) : Resource<Boolean>//Flow<Resource<Boolean>>
    fun checkPassword(password : String,context: Context) : Resource<Boolean>// Flow<Resource<Boolean>>
    suspend fun login (email : String, password : String) : Flow<Resource<LoginResponse>>
}