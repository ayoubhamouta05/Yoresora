package com.youppix.ecommercecourse.presentation.home_app.orders

import com.youppix.ecommercecourse.domain.model.orders.Order

data class OrdersState(
    val isLoading: Boolean = false,
    val allOrders: List<Order> = emptyList(),
    val ordersList: List<Order> = emptyList(),
    val error: String? = null,
    val ordersType: OrdersType = OrdersType.COMPLETED,
)


enum class OrdersType {
    PAID, UNFINISHED, COMPLETED
}