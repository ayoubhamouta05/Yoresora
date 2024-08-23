package com.youppix.ecommercecourse.data.remote.cart.dto

import com.youppix.ecommercecourse.domain.model.cart.CartData
import kotlinx.serialization.Serializable

@Serializable
data class CartResponse(
    val data: ArrayList<CartData>,
    val message: String,
    val status: String
)