package com.youppix.ecommercecourse.di

import android.app.Application
import com.youppix.ecommercecourse.data.manager.LanguageManagerImpl
import com.youppix.ecommercecourse.data.repository.forgotPassword.ForgotPasswordRepositoryImpl
import com.youppix.ecommercecourse.data.repository.login.LoginRepositoryImpl
import com.youppix.ecommercecourse.domain.manager.LanguageManager
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import com.youppix.ecommercecourse.domain.useCases.appLanguage.GetAppLanguageUseCase
import com.youppix.ecommercecourse.domain.useCases.appLanguage.LanguageManagerUseCases
import com.youppix.ecommercecourse.domain.useCases.appLanguage.SaveAppLanguageUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckEmailUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckPasswordUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword.ForgotPasswordUseCases
import com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword.ResetPasswordUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.login.LoginUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Language
    @Provides
    @Singleton
    fun provideLanguageManager(application: Application): LanguageManager {
        return LanguageManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideLanguageManagerUseCases(languageManager: LanguageManager): LanguageManagerUseCases {
        return LanguageManagerUseCases(
            getAppLanguageUseCase = GetAppLanguageUseCase(languageManager),
            saveAppLanguageUseCase = SaveAppLanguageUseCase(languageManager)
        )
    }

    // Login
    @Provides
    @Singleton
    fun provideLoginValidatorRepository(): LoginRepository =
        LoginRepositoryImpl()

    @Provides
    @Singleton
    fun provideLoginUseCases(loginRepository: LoginRepository): LoginUseCases {
        return LoginUseCases(
            checkEmail = CheckEmailUseCase(loginRepository),
            checkPassword = CheckPasswordUseCase(loginRepository)
        )
    }

    // ForgotPassword
    @Provides
    @Singleton
    fun provideForgotPasswordRepository(): ForgotPasswordRepository =
        ForgotPasswordRepositoryImpl()

    @Provides
    @Singleton
    fun provideForgotPasswordUseCases(forgotPasswordRepository: ForgotPasswordRepository): ForgotPasswordUseCases =
        ForgotPasswordUseCases(
            checkEmail = CheckEmailUseCase(forgotPasswordRepository = forgotPasswordRepository),
            checkPassword = CheckPasswordUseCase(forgotPasswordRepository = forgotPasswordRepository),
            resetPassword = ResetPasswordUseCase(forgotPasswordRepository = forgotPasswordRepository)
        )

}