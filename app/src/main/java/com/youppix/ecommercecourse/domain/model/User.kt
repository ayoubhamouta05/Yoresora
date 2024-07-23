package com.youppix.ecommercecourse.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId : Int ,
    val userName : String,
    val userEmail : String ,
    val userPhone : String
)
