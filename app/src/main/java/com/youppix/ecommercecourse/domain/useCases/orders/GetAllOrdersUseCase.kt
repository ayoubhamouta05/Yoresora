package com.youppix.ecommercecourse.domain.useCases.orders

import com.youppix.ecommercecourse.domain.repository.orders.OrdersRepository

class GetAllOrdersUseCase(
    private val ordersRepository: OrdersRepository
) {

}