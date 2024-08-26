package com.youppix.ecommercecourse.common

object Urls {
    private const val BASE_URL = "http://192.168.201.86:8080/ecommerce_course/"
    const val IMAGES_URL = "$BASE_URL/images/"

    /** auth urls */
    const val SIGNUP_URL = "$BASE_URL/auth/signup.php"
    const val VERIFY_CODE_URL = "$BASE_URL/auth/verifyCode.php"
    const val LOGIN_URL = "$BASE_URL/auth/login.php"
    const val CHECK_EMAIL_URL = "$BASE_URL/auth/forgotPassword/checkEmail.php"
    const val VERIFY_CODE_FORGOT_PASSWORD_URL = "$BASE_URL/auth/forgotPassword/verifycode.php"
    const val RESET_PASSWORD_URL = "$BASE_URL/auth/forgotPassword/resetPassword.php"


    /** home urls */
    const val HOME_URL = "$BASE_URL/home.php"
    const val CATEGORIES_URL = "$BASE_URL/categories/categories.php"
    const val COlORS_URL = "$BASE_URL/colors/colors.php"
    const val ITEMS_BY_CATEGORY_URL = "$BASE_URL/items/itemsByCategory.php"
    const val ITEMS_BY_FILTERING_URL = "$BASE_URL/items/itemsByFiltering.php"

    /** details urls */
    const val ITEM_DETAILS_URL = "$BASE_URL/details/details.php"
    const val UPSERT_CUSTOM_SIZE_URL = "$BASE_URL/details/upsertCustomSize.php"
    const val CHECK_SIZE_EXISTENCE_URL = "$BASE_URL/details/checkSizeExistence.php"
    const val ADD_CART_URL = "$BASE_URL/details/addCartItem.php"

    /** favorites urls */
    const val ALL_FAVORITES_URL = "$BASE_URL/favorites/getFavorites.php"
    const val ADD_FAVORITE_URL = "$BASE_URL/favorites/addFavorite.php"

    /** Profile urls */
    const val UPDATE_PROFILE_IMG_URL = "$BASE_URL/profile/updateProfileImage.php"
    const val PROFILE_IMAGES_URL = "$BASE_URL/profileImages/"
    const val UPDATE_PERSONAL_DETAILS_URL = "$BASE_URL/profile/updatePersonalDetails.php"
    const val CHECK_EMAIL_AVAILABILITY_URL = "$BASE_URL/profile/checkEmailAvailability.php"

    /** Cart urls */
    const val ALL_CART_ITEMS_URL = "$BASE_URL/cart/getCartItems.php"
    const val UPDATE_QUANTITY_URL = "$BASE_URL/cart/updateQuantity.php"


}