package com.youppix.ecommercecourse.data.repository.profile

import android.content.Context
import com.youppix.ecommercecourse.common.Constant
import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.auth.dto.AuthResponse
import com.youppix.ecommercecourse.data.remote.profile.ProfileService
import com.youppix.ecommercecourse.domain.manager.LocaleUserEntryManager
import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository
import kotlinx.coroutines.flow.Flow
import java.io.File

class ProfileRepositoryImpl(
    private val profileService : ProfileService,
    private val localeUserEntryManager: LocaleUserEntryManager
) : ProfileRepository {
    override fun saveAppEntry(key: String, value: String) {
        return localeUserEntryManager.saveAppEntry(key, value)
    }

    override suspend fun uploadImage(userId : Int , file: File): Flow<Resource<AuthResponse>> {
        return profileService.uploadImage(userId ,file)
    }

    override fun getUserInfo(key: String, defaultValue: String) : String {
        return localeUserEntryManager.readAppEntry(key, defaultValue)
    }

    override suspend fun updatePersonalDetails(
        userId: Int,
        name: String,
        email: String,
        phone: String,
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<AuthResponse>> {
        return profileService.updatePersonalDetails(userId, name, email, phone, oldPassword, newPassword)
    }

    override fun checkUserName(userName: String, context: Context): Resource<Boolean> {
        return Constant.checkUserName(userName, context)
    }

    override fun checkEmail(email: String, context: Context): Resource<Boolean> {
        return Constant.checkEmail(email, context)
    }

    override fun checkPhone(phone: String, context: Context): Resource<Boolean> {
        return Constant.checkPhone(phone, context)
    }

    override fun checkPassword(password: String, context: Context): Resource<Boolean> {
        return Constant.checkPassword(password, context)
    }

    override suspend fun checkEmailAvailability(
        userId: Int,
        email: String
    ): Flow<Resource<AuthResponse>> {
       return profileService.checkEmailAvailability(userId , email)
    }
}