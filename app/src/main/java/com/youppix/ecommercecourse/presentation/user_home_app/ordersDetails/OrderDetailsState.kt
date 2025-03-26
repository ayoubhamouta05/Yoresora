package com.youppix.ecommercecourse.presentation.user_home_app.ordersDetails

import com.youppix.ecommercecourse.domain.model.orders.OrderDetails

data class OrderDetailsState(
    val isLoading : Boolean = false,
    val items : List<OrderDetails> = emptyList(),
    val error : String? = null
)
