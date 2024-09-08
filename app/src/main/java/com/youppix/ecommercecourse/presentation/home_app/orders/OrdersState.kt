package com.youppix.ecommercecourse.presentation.home_app.orders

data class OrdersState(
    val isLoading : Boolean = false,
    val ordersType : OrdersType = OrdersType.COMPLETED_ORDERS,
)


enum class OrdersType{
    PENDING_DELIVERY , UNFINISHED_ORDERS , COMPLETED_ORDERS
}