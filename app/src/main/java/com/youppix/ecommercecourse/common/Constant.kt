package com.youppix.ecommercecourse.common

import android.content.Context
import android.util.Log
import android.util.Patterns
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.domain.model.OnBoarding

object Constant {

    const val APP_LANG = "APP_LANG"

    val pages = listOf(
        OnBoarding(
            title = R.string.onboardingtitle1,
            image = R.drawable.onboarding1,
            body = R.string.onboardingbody1
        ),
        OnBoarding(
            title = R.string.onboardingtitle2,
            image = R.drawable.onboarding2,
            body = R.string.onboardingbody2
        ),
        OnBoarding(
            title = R.string.onboardingtitle3,
            image = R.drawable.onboarding3,
            body = R.string.onboardingbody3
        ),

    )

    fun checkEmail(email: String , context: Context): Resource<Boolean> {
        val trimEmail = email.trim()

        return when {
            trimEmail.isBlank() || trimEmail.isEmpty() -> {
                Resource.Error(context.getString(R.string.emailEmptyErrorMsg), false)
            }
            !Patterns.EMAIL_ADDRESS.matcher(trimEmail).matches() -> {
                Resource.Error(context.getString(R.string.emailWrongPatternErrorMsg), false)
            }
            else -> {
                Resource.Successful(true)
            }
        }
    }

    fun checkPassword(password: String , context: Context): Resource<Boolean> {
        val trimPassword = password.trim()

        return when {
            trimPassword.isBlank() || trimPassword.isEmpty() -> {
                Resource.Error(context.getString(R.string.passwordEmptyErrorMsg), false)
            }
            trimPassword.length < 6 -> {
                Resource.Error(context.getString(R.string.passwordWrongPatternErrorMsg), false)
            }
            else -> {
                Resource.Successful(true)
            }
        }
    }
}