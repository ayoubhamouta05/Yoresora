package com.youppix.ecommercecourse.domain.useCases.orders

data class OrdersUseCases(
    val getAllOrders: GetAllOrdersUseCase,
    val getOrderDetails : GetOrderDetailsUseCase
)