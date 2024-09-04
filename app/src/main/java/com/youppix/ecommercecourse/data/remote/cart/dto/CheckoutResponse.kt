package com.youppix.ecommercecourse.data.remote.cart.dto

import com.youppix.ecommercecourse.domain.model.address.Address
import kotlinx.serialization.Serializable

@Serializable
data class CheckoutResponse (
    val status : String ,
    val message : String ,
    val data : Address?= null ,
    val deliveryHomePrice : Float =0f,
    val deliveryPickupPointPrice : Float = 0f,
)