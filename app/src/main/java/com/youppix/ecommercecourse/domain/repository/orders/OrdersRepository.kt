package com.youppix.ecommercecourse.domain.repository.orders

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.orders.dto.OrderDetailsResponse
import com.youppix.ecommercecourse.data.remote.orders.dto.OrdersResponse
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {

    suspend fun getAllOrders(userId: Int) : Flow<Resource<OrdersResponse>>

    suspend fun getOrderDetails(orderId : String) : Flow<Resource<OrderDetailsResponse>>

}