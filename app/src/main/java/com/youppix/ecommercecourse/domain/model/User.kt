package com.youppix.ecommercecourse.domain.model


//data class User(
////    @SerialName("users_approve")
//    val usersApprove: Int = 0,
////    @SerialName("users_create")
//    val usersCreate: String = "",
////    @SerialName("users_email")
//    val usersEmail: String,
////    @SerialName("users_id")
//    val usersId: Int = 0,
////    @SerialName("users_name")
//    val usersName: String,
////    @SerialName("users_password")
//    val usersPassword: String,
////    @SerialName("users_phone")
//    val usersPhone: String,
////    @SerialName("users_verifycode")
//    val usersVerifyCode: Int = 0
//)
data class User(
    val usersName: String,
    val usersPassword: String,
    val usersEmail: String,
    val usersPhone: String
)