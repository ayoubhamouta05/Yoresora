package com.youppix.ecommercecourse.data.repository.orders

import com.youppix.ecommercecourse.data.remote.orders.OrdersService
import com.youppix.ecommercecourse.domain.repository.orders.OrdersRepository

class OrdersRepositoryImpl(private val ordersService: OrdersService) : OrdersRepository {

}