package com.youppix.ecommercecourse.data.remote.auth.dto

import com.youppix.ecommercecourse.domain.model.UserDataResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val data: UserDataResponse? = null,
    val message: String,
    val status: String
)