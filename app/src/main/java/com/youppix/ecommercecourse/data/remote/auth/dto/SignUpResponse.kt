package com.youppix.ecommercecourse.data.remote.auth.dto
import kotlinx.serialization.Serializable


@Serializable
data class SignUpResponse(
    val status : String ,
    val message : String
)