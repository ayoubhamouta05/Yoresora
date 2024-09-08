package com.youppix.ecommercecourse.data.repository.checkout

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.cart.CartService
import com.youppix.ecommercecourse.data.remote.cart.dto.CheckoutResponse
import com.youppix.ecommercecourse.data.remote.cart.dto.CreateCheckoutUrlResponse
import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.model.items.Item
import com.youppix.ecommercecourse.domain.repository.checkout.CheckoutRepository
import kotlinx.coroutines.flow.Flow

class CheckoutRepositoryImpl(private val service: CartService) : CheckoutRepository {
    override suspend fun getAddress(userId: Int): Flow<Resource<CheckoutResponse>> {
        return service.getAddress(userId)
    }

    override suspend fun createCheckout(
        local: String,
        description: String,
        amount: Float,
        customerId: String,
        userId: Int,
        carts : List<CartData>
    ): Flow<Resource<CreateCheckoutUrlResponse>> {
        return service.createCheckout(
            local = local,
            description = description,
            amount = amount,
            customerId = customerId,
            userId = userId,
            carts = carts
        )
    }
}