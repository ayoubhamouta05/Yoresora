package com.youppix.ecommercecourse.domain.useCases.orders

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.orders.dto.OrderDetailsResponse
import com.youppix.ecommercecourse.data.remote.orders.dto.OrdersResponse
import com.youppix.ecommercecourse.domain.repository.orders.OrdersRepository
import kotlinx.coroutines.flow.Flow

class GetOrderDetailsUseCase(
    private val ordersRepository: OrdersRepository
) {
    suspend operator fun invoke(orderId : String) : Flow<Resource<OrderDetailsResponse>> =
        ordersRepository.getOrderDetails(orderId)
}