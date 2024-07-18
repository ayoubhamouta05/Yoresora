package com.youppix.ecommercecourse.domain.useCases.appEntry

import com.youppix.ecommercecourse.domain.manager.LocaleUserEntryManager

class GetAppEntryUseCase(
    private val localeUserEntryManager: LocaleUserEntryManager
) {
    operator fun invoke(key: String, defaultValue: Boolean): Boolean {
        return localeUserEntryManager.readAppEntry(key, defaultValue)
    }
}