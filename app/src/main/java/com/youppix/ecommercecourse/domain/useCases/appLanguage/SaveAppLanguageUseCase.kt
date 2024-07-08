package com.youppix.ecommercecourse.domain.useCases.appLanguage

import com.youppix.ecommercecourse.domain.manager.LanguageManager

class SaveAppLanguageUseCase(
    private val languageManager : LanguageManager
) {
    suspend operator fun invoke(key : String , value : String ){
        languageManager.saveLanguage(key , value)
    }
}