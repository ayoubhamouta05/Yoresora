package com.youppix.ecommercecourse.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val usersName : String,
    val usersEmail : String ,
    val usersPassword : String ,
    val usersPhone : String
)
