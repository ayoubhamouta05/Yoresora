package com.youppix.ecommercecourse.data.repository.profile

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
}