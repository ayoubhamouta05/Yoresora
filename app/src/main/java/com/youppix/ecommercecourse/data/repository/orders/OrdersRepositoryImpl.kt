package com.youppix.ecommercecourse.data.repository.orders

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.orders.OrdersService
import com.youppix.ecommercecourse.data.remote.orders.dto.OrderDetailsResponse
import com.youppix.ecommercecourse.data.remote.orders.dto.OrdersResponse
import com.youppix.ecommercecourse.domain.repository.orders.OrdersRepository
import kotlinx.coroutines.flow.Flow

class OrdersRepositoryImpl(private val ordersService: OrdersService) : OrdersRepository {

    override suspend fun getAllOrders(userId: Int): Flow<Resource<OrdersResponse>> {
        return ordersService.getAllOrders(userId)
    }

    override suspend fun getOrderDetails(orderId: String): Flow<Resource<OrderDetailsResponse>> {
        return ordersService.getOrderDetails(orderId)
    }

}