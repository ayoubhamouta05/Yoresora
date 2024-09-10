package com.youppix.ecommercecourse.presentation.home_app.orders

sealed class OrdersEvent{
    data class UpdateOrderType (val type : OrdersType) : OrdersEvent()
    data class GetAllOrders (val userId : Int) : OrdersEvent()

}
