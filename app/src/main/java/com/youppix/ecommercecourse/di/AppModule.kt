package com.youppix.ecommercecourse.di

import android.app.Application
import com.youppix.ecommercecourse.data.manager.LanguageManagerImpl
import com.youppix.ecommercecourse.data.manager.LocaleUserEntryManagerImpl
import com.youppix.ecommercecourse.data.repository.forgotPassword.ForgotPasswordRepositoryImpl
import com.youppix.ecommercecourse.data.repository.login.LoginRepositoryImpl
import com.youppix.ecommercecourse.data.repository.signUp.SignUpRepositoryImpl
import com.youppix.ecommercecourse.domain.manager.LanguageManager
import com.youppix.ecommercecourse.domain.manager.LocaleUserEntryManager
import com.youppix.ecommercecourse.domain.repository.forgotPassword.ForgotPasswordRepository
import com.youppix.ecommercecourse.domain.repository.login.LoginRepository
import com.youppix.ecommercecourse.domain.repository.signUp.SignUpRepository
import com.youppix.ecommercecourse.domain.useCases.appEntry.AppEntryUseCases
import com.youppix.ecommercecourse.domain.useCases.appEntry.GetAppEntryUseCase
import com.youppix.ecommercecourse.domain.useCases.appEntry.SaveAppEntryUseCase
import com.youppix.ecommercecourse.domain.useCases.appLanguage.GetAppLanguageUseCase
import com.youppix.ecommercecourse.domain.useCases.appLanguage.LanguageManagerUseCases
import com.youppix.ecommercecourse.domain.useCases.appLanguage.SaveAppLanguageUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckEmailUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.CheckPasswordUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword.ForgotPasswordUseCases
import com.youppix.ecommercecourse.domain.useCases.auth.forgotPassword.ResetPasswordUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.login.LoginUseCases
import com.youppix.ecommercecourse.domain.useCases.auth.signUp.CheckPhoneUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.signUp.CheckUserNameUseCase
import com.youppix.ecommercecourse.domain.useCases.auth.signUp.SignUpUseCases
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

    // SignUp
    @Provides
    @Singleton
    fun provideSignUpRepository() : SignUpRepository =
        SignUpRepositoryImpl()

    @Provides
    @Singleton
    fun provideSignUpUseCases(signUpRepository : SignUpRepository) : SignUpUseCases =
        SignUpUseCases(
            checkEmail = CheckEmailUseCase(signUpRepository = signUpRepository),
            checkPassword = CheckPasswordUseCase(signUpRepository = signUpRepository),
            checkUserName = CheckUserNameUseCase(signUpRepository = signUpRepository),
            checkPhone = CheckPhoneUseCase(signUpRepository = signUpRepository)
        )


    @Provides
    @Singleton
    fun provideLocaleUserEntryManager(application: Application) : LocaleUserEntryManager =
        LocaleUserEntryManagerImpl(application)

    @Provides
    @Singleton
    fun provideLocaleUserEntryUseCases(localeUserEntryManager: LocaleUserEntryManager) : AppEntryUseCases =
        AppEntryUseCases(
            getAppEntryUseCase = GetAppEntryUseCase(localeUserEntryManager),
            saveAppEntryUseCase = SaveAppEntryUseCase(localeUserEntryManager)
        )


}