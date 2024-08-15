package com.youppix.ecommercecourse.data.repository.profile

import com.youppix.ecommercecourse.data.remote.profile.ProfileService
import com.youppix.ecommercecourse.domain.manager.LocaleUserEntryManager
import com.youppix.ecommercecourse.domain.repository.profile.ProfileRepository

class ProfileRepositoryImpl(
    private val profileService : ProfileService,
    private val localeUserEntryManager: LocaleUserEntryManager
) : ProfileRepository {

}