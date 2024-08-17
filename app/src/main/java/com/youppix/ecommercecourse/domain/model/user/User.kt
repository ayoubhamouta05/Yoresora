package com.youppix.ecommercecourse.domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId : Int = 0 ,
    val userName : String= "",
    val userEmail : String ="",
    val userPhone : String ="",
    val userImage : String ? = null
)
