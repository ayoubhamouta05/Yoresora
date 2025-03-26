package com.youppix.ecommercecourse.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserDataResponse(
    val users_approve: Int,
    val users_create: String,
    val users_email: String,
    val users_id: Int,
    val users_customer_id : String?,
    val users_name: String,
    val users_password: String,
    val users_phone: String,
    val users_verifycode: Int ,
    val users_image : String? ,
    val users_type : Int
)

fun UserDataResponse.toUser(): User {
    return User(
        userName = users_name,
        userId = users_id,
        userCustomerId = users_customer_id,
        userEmail = users_email,
        userPhone = users_phone ,
        userImage = users_image ,
        userType = users_type
    )
}
