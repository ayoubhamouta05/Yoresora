package com.youppix.ecommercecourse.common

import android.content.Context
import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import com.youppix.ecommercecourse.R
import com.youppix.ecommercecourse.domain.model.bottomBar.BottomBar
import com.youppix.ecommercecourse.domain.model.onBoarding.OnBoarding
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

object Constant {

    const val APP_LANG = "APP_LANG"

    const val COUNTRY_CODE = "213+"

    const val APP_ENTRY = "appEntry"

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

    val bottomBarItems = listOf(
        BottomBar(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            screen = 0
        ),
        BottomBar(
            title = "Shop",
            selectedIcon = Icons.Filled.ShoppingBag,
            unselectedIcon = Icons.Outlined.ShoppingBag,
            screen = 1
        ),
        BottomBar(
            title = "Favorite",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            screen = 2
        ),
        BottomBar(
            title = "Chat",
            selectedIcon = Icons.AutoMirrored.Filled.Message,
            unselectedIcon = Icons.AutoMirrored.Outlined.Message,
            screen = 3
        ),
        BottomBar(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            screen = 4
        ),


        )

    val lANG_LIST = listOf(
        Pair(R.string.english,"en"),
        Pair(R.string.arabic ,"ar")
    )

    fun setLocal(lang: String, context: Context) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()

        config.setLocale(locale)
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }


    fun checkUserName(name: String, context: Context): Resource<Boolean> {
        val regex = "^[A-Za-z][A-Za-z0-9_]*( [A-Za-z0-9_]+)*$"
        val p: Pattern = Pattern.compile(regex)
        val trimName = name.trim()
        val m: Matcher = p.matcher(name)
        return when {
            name.isBlank() || trimName.isEmpty() -> {
                Resource.Error(context.getString(R.string.userNameEmptyErrorMsg), false)
            }

            !m.matches() -> {
                Resource.Error(context.getString(R.string.userNameWrongPatternErrorMsg), false)
            }

            else -> {
                Resource.Successful(true)
            }
        }
    }

    fun checkPhone(phone: String, context: Context): Resource<Boolean> {
        val regex = "^(00213|\\+213|0)(5|6|7)[0-9]{8}$"
        val p: Pattern = Pattern.compile(regex)
        val trimPhone = phone.trim()
        val m: Matcher = p.matcher(phone)
        return when {
            phone.isBlank() || trimPhone.isEmpty() -> {
                Resource.Error(context.getString(R.string.phoneEmptyErrorMsg), false)
            }

            !m.matches() -> {
                Resource.Error(context.getString(R.string.phoneWrongPatternErrorMsg), false)
            }

            else -> {
                Resource.Successful(true)
            }
        }
    }

    fun checkEmail(email: String, context: Context): Resource<Boolean> {
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

    fun checkPassword(password: String, context: Context): Resource<Boolean> {
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