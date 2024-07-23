package com.youppix.ecommercecourse.domain.repository.signUp

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.domain.model.User
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {

    fun checkUserName(userName : String , context: Context) : Resource<Boolean>
    fun checkEmail (email : String , context: Context) : Resource<Boolean>//Flow<Resource<Boolean>>
    fun checkPhone(phone : String , context : Context) : Resource<Boolean>
    fun checkPassword(password : String,context: Context) : Resource<Boolean>// Flow<Resource<Boolean>>
    suspend fun addUser(name : String , email : String , phone : String , password : String) : Flow<Resource<AuthResponse>>
    suspend fun verifyCode(email  :String , verifyCode: String) : Flow<Resource<AuthResponse>>
}