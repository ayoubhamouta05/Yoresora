package com.youppix.ecommercecourse.di

import android.app.Application
import com.youppix.ecommercecourse.data.manager.LanguageManagerImpl
import com.youppix.ecommercecourse.domain.manager.LanguageManager
import com.youppix.ecommercecourse.domain.useCases.appLanguage.GetAppLanguageUseCase
import com.youppix.ecommercecourse.domain.useCases.appLanguage.LanguageManagerUseCases
import com.youppix.ecommercecourse.domain.useCases.appLanguage.SaveAppLanguageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
class AppModule() {

    @Provides
    @Inject
    fun provideLanguageManager(application: Application): LanguageManager {
        return LanguageManagerImpl(application)
    }

    @Provides
    @Inject
    fun provideLanguageManagerUseCases(languageManager: LanguageManager): LanguageManagerUseCases {
        return LanguageManagerUseCases(
            getAppLanguageUseCase = GetAppLanguageUseCase(languageManager),
            saveAppLanguageUseCase = SaveAppLanguageUseCase(languageManager)
        )
    }

}