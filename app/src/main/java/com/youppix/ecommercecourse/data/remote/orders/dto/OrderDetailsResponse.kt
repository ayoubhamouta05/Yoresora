package com.youppix.ecommercecourse.data.remote.orders.dto

import com.youppix.ecommercecourse.domain.model.orders.OrderDetails
import kotlinx.serialization.Serializable

@Serializable
data class OrderDetailsResponse(
    val data: List<OrderDetails> ? = null,
    val message: String,
    val status: String
)