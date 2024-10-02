package com.youppix.ecommercecourse.common

object Urls {
    private const val BASE_URL = "http://192.168.152.86:8080/ecommerce_course/"
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
    const val COLORS_URL = "$BASE_URL/colors/colors.php"
    const val ITEMS_BY_CATEGORY_URL = "$BASE_URL/items/itemsByCategory.php"
    const val ITEMS_BY_FILTERING_URL = "$BASE_URL/items/itemsByFiltering.php"
    const val GET_NOTIFICATIONS_URL = "$BASE_URL/notifications/getNotifications.php"
    const val UPDATE_NOTIFICATION_URL = "$BASE_URL/notifications/updateNotification.php"

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
    const val GET_ADDRESS_URL = "$BASE_URL/address/getAddress.php"
    const val CREATE_CHECKOUT_URL = "$BASE_URL/checkout/createCheckout.php"
    const val FAILURE_URL = "/ecommerce_course/webhook/failure.php"
    const val SUCCESS_URL = "/ecommerce_course/webhook/success.php"

    /** Address urls */
    const val GET_ALL_ADDRESS_URL = "$BASE_URL/address/getAllAddress.php"
    const val UPSERT_ADDRESS_URL = "$BASE_URL/address/upsertAddress.php"
    const val DELETE_ADDRESS_URL = "$BASE_URL/address/deleteAddress.php"
    const val GET_COMMUNE_URL = "$BASE_URL/address/getCommune.php"

    /** Order urls */
    const val GET_ALL_ORDERS_URL = "$BASE_URL/orders/getAllOrders.php"
    const val GET_ORDER_DETAILS_URL = "$BASE_URL/orders/getOrderDetails.php"

}