package com.youppix.ecommercecourse.domain.useCases.appLanguage

import com.youppix.ecommercecourse.domain.manager.LanguageManager

class GetAppLanguageUseCase(
    private val languageManager: LanguageManager
) {
    operator fun invoke(key: String, defaultValue: String): String {
        return languageManager.getLanguage(key, defaultValue)
    }
}