package com.youppix.ecommercecourse.domain.useCases.appEntry

import com.youppix.ecommercecourse.domain.manager.LocaleUserEntryManager

class SaveAppEntryUseCase(
    private val localeUserEntryManager: LocaleUserEntryManager
) {
    operator fun invoke(key: String, value: Boolean) {
        return localeUserEntryManager.saveAppEntry(key, value)
    }
}