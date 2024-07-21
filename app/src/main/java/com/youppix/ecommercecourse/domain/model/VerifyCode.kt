package com.youppix.ecommercecourse.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class VerifyCode(
    val email: String,
    val verifycode: String
)
