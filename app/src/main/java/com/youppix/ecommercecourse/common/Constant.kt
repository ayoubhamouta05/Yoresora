package com.youppix.ecommercecourse.common

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
}