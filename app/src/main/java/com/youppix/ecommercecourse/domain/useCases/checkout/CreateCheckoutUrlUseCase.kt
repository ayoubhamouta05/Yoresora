package com.youppix.ecommercecourse.domain.useCases.checkout

import com.youppix.ecommercecourse.domain.model.cart.CartData
import com.youppix.ecommercecourse.domain.repository.checkout.CheckoutRepository

class CreateCheckoutUrlUseCase(
    private val checkoutRepository: CheckoutRepository,
) {

    suspend operator fun invoke(
        local: String,
        description: String,
        amount: Float,
        customerId: String,
        userId: Int,
        carts: List<CartData>,
    ) =
        checkoutRepository.createCheckout(
            local = local,
            description = description,
            amount = amount,
            customerId = customerId,
            userId = userId,
            carts = carts
        )

}
