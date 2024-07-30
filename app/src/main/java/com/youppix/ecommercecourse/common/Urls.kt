package com.youppix.ecommercecourse.common

object Urls {
    const val BASE_URL = "http://192.168.201.86:8080/ecommerce_course/"

    /** auth urls */
    const val SIGNUP_URL = "$BASE_URL/auth/signup.php"
    const val VERIFY_CODE_URL = "$BASE_URL/auth/verifyCode.php"
    const val LOGIN_URL = "$BASE_URL/auth/login.php"
    const val CHECK_EMAIL_URL = "$BASE_URL/auth/forgotPassword/checkEmail.php"
    const val VERIFY_CODE_FORGOT_PASSWORD_URL = "$BASE_URL/auth/forgotPassword/verifycode.php"
    const val RESET_PASSWORD_URL = "$BASE_URL/auth/forgotPassword/resetPassword.php"
    const val ALL_ITEMS_URL = "$BASE_URL/items/items.php"
    const val ITEMS_BY_CATEGORY_URL = "$BASE_URL/items/itemsByCategory.php"

    /** home urls */
    const val HOME_URL = "$BASE_URL/home.php"
    const val IMAGES_URL = "$BASE_URL/images/"

}