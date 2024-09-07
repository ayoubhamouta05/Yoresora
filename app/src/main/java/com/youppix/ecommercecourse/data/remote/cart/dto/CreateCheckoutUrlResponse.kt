package com.youppix.ecommercecourse.data.remote.cart.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCheckoutUrlResponse(
    val status : String ,
    val message : String ,
    val data : String ?= null
)
