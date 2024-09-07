package com.youppix.ecommercecourse.domain.repository.checkout

import com.youppix.ecommercecourse.common.Resource
import com.youppix.ecommercecourse.data.remote.cart.dto.CheckoutResponse
import com.youppix.ecommercecourse.data.remote.cart.dto.CreateCheckoutUrlResponse
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {
    suspend fun getAddress(userId: Int): Flow<Resource<CheckoutResponse>>

    suspend fun createCheckout(
        local: String,
        description: String,
        amount: Float,
        customerId: String,
        userId: Int
    ): Flow<Resource<CreateCheckoutUrlResponse>>
}