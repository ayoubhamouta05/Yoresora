package com.youppix.ecommercecourse.common

import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.domain.model.OnBoarding

object Constant {

    val pages = listOf(
        OnBoarding(
            title = "Welcome to OM-MH",
            image = R.drawable.onboarding1,
            body = "Discover exquisite couture and custom-made dresses designed just for you"
        ),
        OnBoarding(
            title = "Expert Craftsmanship",
            image = R.drawable.onboarding2,
            body = "Experience the finest in dressmaking with our skilled artisans and attention to detail"
        ),
        OnBoarding(
            title = "Tailored to You",
            image = R.drawable.onboarding3,
            body = "Every piece is tailored to fit you perfectly, reflecting your unique style and personality"
        ),

    )
}