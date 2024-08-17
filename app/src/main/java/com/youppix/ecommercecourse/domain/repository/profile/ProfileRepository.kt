package com.youppix.ecommercecourse.domain.repository.profile

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

interface ProfileRepository {

    fun saveAppEntry(key: String, value: String)
    suspend fun uploadImage(userId : Int ,file: File): Flow<Resource<AuthResponse>>

    fun getUserInfo(key : String , defaultValue :String) : String

}