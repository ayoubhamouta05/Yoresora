package com.youppix.ecommercecourse.presentation.user_home_app.ordersDetails

sealed class OrderDetailsEvent {

    data class GetOrderDetails(val orderId: String) : OrderDetailsEvent()

}