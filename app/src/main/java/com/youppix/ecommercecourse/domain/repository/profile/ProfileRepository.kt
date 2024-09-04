package com.youppix.ecommercecourse.domain.repository.profile

import android.content.Context
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

interface ProfileRepository {

    fun saveAppEntry(key: String, value: String)
    suspend fun uploadImage(userId : Int ,file: File): Flow<Resource<AuthResponse>>

    fun getUserInfo(key : String , defaultValue :String) : String

    suspend fun updatePersonalDetails(
        userId: Int,
        name: String,
        email: String,
        phone: String,
        oldPassword: String,
        newPassword: String,
        userCustomerId : String
    ): Flow<Resource<AuthResponse>>

    fun checkUserName(userName : String , context: Context) : Resource<Boolean>
    fun checkEmail (email : String , context: Context) : Resource<Boolean>
    fun checkPhone(phone : String , context : Context) : Resource<Boolean>
    fun checkPassword(password : String,context: Context) : Resource<Boolean>

    suspend fun checkEmailAvailability(userId : Int , email : String ) : Flow<Resource<AuthResponse>>

}