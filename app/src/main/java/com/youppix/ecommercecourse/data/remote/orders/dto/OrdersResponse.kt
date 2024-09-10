package com.youppix.ecommercecourse.data.remote.orders.dto

import com.youppix.ecommercecourse.domain.model.orders.Order
import kotlinx.serialization.Serializable

@Serializable
data class OrdersResponse(
    val data: List<Order>? = null,
    val message: String,
    val status: String
)